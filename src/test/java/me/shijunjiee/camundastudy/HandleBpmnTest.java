package me.shijunjiee.camundastudy;

import jakarta.annotation.Resource;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.Query;
import org.camunda.bpm.model.bpmn.builder.ServiceTaskBuilder;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaIn;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaOut;
import org.camunda.bpm.model.xml.ModelInstance;
import org.camunda.bpm.model.xml.instance.DomElement;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CamundaStudyApplication.class)
public class HandleBpmnTest {

    @Resource
    private RepositoryService repositoryService;

    @Test
    public void testApi() {
        // XML 文件路径
        String filePath = "bpmn/Process_testnode4j.bpmn";

        // 使用 ClassLoader 获取文件流
        InputStream inputStream = HandleBpmnTest.class.getClassLoader().getResourceAsStream(filePath);


        try {
            // 将BPMN文件转换成BpmnModelInstance对象
            BpmnModelInstance modelInstance = Bpmn.readModelFromStream(inputStream);

            // 为流程添加可执行属性
            Collection<Process> processes = modelInstance.getModelElementsByType(Process.class);
            Process process = processes.stream().findFirst().get();
            process.setExecutable(true);

            // 为service task添加异步属性
            Collection<ServiceTask> serviceTasks = modelInstance.getModelElementsByType(ServiceTask.class);
            serviceTasks.stream().forEach(serviceTask -> serviceTask.setCamundaAsyncBefore(true));

            // 为call activity添加异步属性和business key传递以及上下文传递
            Collection<CallActivity> callActivities = modelInstance.getModelElementsByType(CallActivity.class);
            callActivities.stream().forEach(callActivity -> {
                callActivity.setCamundaAsyncBefore(true);
                ExtensionElements extensionElements = callActivity.getExtensionElements();
                if (extensionElements == null) {
                    extensionElements = modelInstance.newInstance(ExtensionElements.class);
                    callActivity.setExtensionElements(extensionElements);
                }
                extensionElements.addExtensionElement(CamundaIn.class).setCamundaBusinessKey("#{execution.processBusinessKey}");
                extensionElements.addExtensionElement(CamundaIn.class).setCamundaVariables("all");
                extensionElements.addExtensionElement(CamundaOut.class).setCamundaVariables("all");
            });

            Deployment api = repositoryService.createDeployment().addModelInstance(process.getId() + ".bpmn", modelInstance).name(process.getId()).deploy();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
