package me.shijunjiee.camundastudy.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.shijunjiee.camundastudy.dto.BpmnDTO;
import me.shijunjiee.camundastudy.dto.SequenceFlowDTO;
import me.shijunjiee.camundastudy.dto.ServiceTaskDTO;
import me.shijunjiee.camundastudy.dto.UserTaskDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class QueryController {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;


    @GetMapping("/instance/execution/{processInstanceId}")
    public void testQuery(@PathVariable("processInstanceId") String processInstanceId) {
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstanceId);
        System.out.println();
    }

    @PostMapping("/deployJSON")
    public void convertBPMNToJson(@RequestBody BpmnDTO request) {
        Map<String, FlowNode> flowNodeMap = new HashMap<>();
        Map<String, SequenceFlow> sequenceFlowMap = new HashMap<>();
        BpmnModelInstance modelInstance = Bpmn.createEmptyModel();

        // 根据自定义规则和逻辑构建 BPMN 元素
        // 创建流程元素
        Definitions definitions = modelInstance.newInstance(Definitions.class);
        definitions.setId("Definitions_" + RandomStringUtils.randomAlphabetic(8));
        definitions.setTargetNamespace("http://bpmn.io/schema/bpmn");
        modelInstance.setDefinitions(definitions);

        Process process = modelInstance.newInstance(Process.class);
        process.setId(request.getProcess().getId());
        if (StringUtils.isNotBlank(request.getProcess().getName())) {
            process.setName(request.getProcess().getName());
        }

        process.setExecutable(request.getProcess().isExecutable());
        if (StringUtils.isNotBlank(request.getProcess().getHistoryTimeToLive())) {
            process.setCamundaHistoryTimeToLiveString(request.getProcess().getHistoryTimeToLive());
        }
        modelInstance.getDefinitions().addChildElement(process);


        // 创建开始事件
        StartEvent startEvent = modelInstance.newInstance(StartEvent.class);
        startEvent.setId(request.getProcess().getStartEvent().getId());
        startEvent.setName(request.getProcess().getStartEvent().getName());
        if (StringUtils.isNotBlank(request.getProcess().getStartEvent().getInitiator())) {
            startEvent.setCamundaInitiator(request.getProcess().getStartEvent().getInitiator());
        }
        // 创建输出连线并将其与开始事件关联
        flowNodeMap.put(startEvent.getId(), startEvent);
        process.addChildElement(startEvent);

        // 创建结束事件
        EndEvent endEvent = modelInstance.newInstance(EndEvent.class);
        endEvent.setId(request.getProcess().getEndEvent().getId());
        endEvent.setName(request.getProcess().getEndEvent().getName());
        // 创建输出连线并将其与结束事件关联
        flowNodeMap.put(endEvent.getId(), endEvent);
        process.addChildElement(endEvent);

        // 创建服务任务
        for (ServiceTaskDTO serviceTaskDTO : request.getProcess().getServiceTasks()) {
            ServiceTask serviceTask = modelInstance.newInstance(ServiceTask.class);
            serviceTask.setId(serviceTaskDTO.getId());
            serviceTask.setName(serviceTaskDTO.getName());
            if (StringUtils.isNotBlank(serviceTaskDTO.getType())) {
                serviceTask.setCamundaType(serviceTaskDTO.getType());
            }
            if (StringUtils.isNotBlank(serviceTaskDTO.getTopic())) {
                serviceTask.setCamundaTopic(serviceTaskDTO.getTopic());
            }
            if (StringUtils.isNotBlank(serviceTaskDTO.getDelegateExpression())) {
                serviceTask.setCamundaDelegateExpression(serviceTaskDTO.getDelegateExpression());
            }
            flowNodeMap.put(serviceTask.getId(), serviceTask);
            process.addChildElement(serviceTask);
        }

        // 创建用户任务
        for (UserTaskDTO userTaskDTO : request.getProcess().getUserTasks()) {
            UserTask userTask = modelInstance.newInstance(UserTask.class);
            userTask.setId(userTaskDTO.getId());
            userTask.setName(userTaskDTO.getName());
            userTask.setCamundaAssignee(userTaskDTO.getAssignee());
            flowNodeMap.put(userTask.getId(), userTask);
            process.addChildElement(userTask);
        }

        // 创建流程连线
        for (SequenceFlowDTO sequenceFlowDTO : request.getProcess().getSequenceFlows()) {
            SequenceFlow flow = modelInstance.newInstance(SequenceFlow.class);
            flow.setId(sequenceFlowDTO.getId());
            flow.setSource(flowNodeMap.get(sequenceFlowDTO.getSourceRef()));
            flow.setTarget(flowNodeMap.get(sequenceFlowDTO.getTargetRef()));
            sequenceFlowMap.put(flow.getId(), flow);
            process.addChildElement(flow);
        }

        // 设置连线
        setOutgoing(startEvent, sequenceFlowMap.get(request.getProcess().getStartEvent().getOutgoing()));
        setIncoming(endEvent, sequenceFlowMap.get(request.getProcess().getEndEvent().getIncoming()));
        for (ServiceTaskDTO serviceTaskDTO : request.getProcess().getServiceTasks()) {
            FlowNode flowNode = flowNodeMap.get(serviceTaskDTO.getId());
            flowNode.getIncoming().add(sequenceFlowMap.get(serviceTaskDTO.getIncoming()));
            flowNode.getOutgoing().add(sequenceFlowMap.get(serviceTaskDTO.getOutgoing()));
        }
        for (UserTaskDTO userTaskDTO : request.getProcess().getUserTasks()) {
            FlowNode flowNode = flowNodeMap.get(userTaskDTO.getId());
            flowNode.getIncoming().add(sequenceFlowMap.get(userTaskDTO.getIncoming()));
            flowNode.getOutgoing().add(sequenceFlowMap.get(userTaskDTO.getOutgoing()));
        }

//        // 创建 BPMNDiagram
//        Diagram bpmnDiagram = modelInstance.newInstance(Diagram.class);
//        bpmnDiagram.setId("BPMNDiagram_1");
//
//        // 创建 BPMNPlane 并与 BPMNDiagram 关联
//        Plane bpmnPlane = modelInstance.newInstance(Plane.class);
//        bpmnPlane.setId("BPMNPlane_1");
//        bpmnPlane.setAttributeValue("bpmnElement", process.getId()); // 设置 BPMNPlane 关联的流程元素
//        bpmnDiagram.addChildElement(bpmnPlane);
//
//        // 创建 BPMNShape 并设置开始事件位置
//        Shape startEventShape = modelInstance.newInstance(Shape.class);
//        startEventShape.setId("_BPMNShape_StartEvent_1");
//        startEventShape.setBpmnElement(startEvent); // 设置 BPMNShape 关联的开始事件
//        startEventShape.setBounds(/* 设置开始事件的位置和大小 */);
//        bpmnPlane.getDiagramElements().add(startEventShape); // 将 BPMNShape 添加到 BPMNPlane 中
//
//        // 创建 BPMNShape 并设置结束事件位置
//        Shape endEventShape = modelInstance.newInstance(Shape.class);
//        endEventShape.setId("_BPMNShape_EndEvent_1");
//        endEventShape.setBpmnElement(endEvent); // 设置 BPMNShape 关联的结束事件
//        endEventShape.setBounds(/* 设置结束事件的位置和大小 */);
//        bpmnPlane.getDiagramElements().add(endEventShape); // 将 BPMNShape 添加到 BPMNPlane 中
//
//        // 创建 BPMNEdge 并设置流程连线位置
//        Edge sequenceFlowEdge = modelInstance.newInstance(Edge.class);
//        sequenceFlowEdge.setId("_BPMNEdge_SequenceFlow_1");
//        sequenceFlowEdge.setBpmnElement(sequenceFlow); // 设置 BPMNEdge 关联的流程连线
//        sequenceFlowEdge.setSourceElement(startEventShape); // 设置流程连线的起始元素
//        sequenceFlowEdge.setTargetElement(endEventShape); // 设置流程连线的目标元素
//        sequenceFlowEdge.getWaypoints().add(/* 设置流程连线的路径 */);
//        bpmnPlane.getDiagramElements().add(sequenceFlowEdge); // 将 BPMNEdge 添加到 BPMNPlane 中
//
//        // 将 BPMNDiagram 添加到 BPMN 模型定义中
//        definitions.addChildElement(bpmnDiagram);

        Deployment api = repositoryService.createDeployment().addModelInstance(request.getProcess().getId() + ".bpmn", modelInstance).name(request.getProcess().getName()).deploy();
    }

    private void setIncoming(FlowNode flowNode, SequenceFlow sequenceFlow) {
        flowNode.getIncoming().add(sequenceFlow);
    }

    private void setOutgoing(FlowNode flowNode, SequenceFlow sequenceFlow) {
        flowNode.getOutgoing().add(sequenceFlow);
    }


}
