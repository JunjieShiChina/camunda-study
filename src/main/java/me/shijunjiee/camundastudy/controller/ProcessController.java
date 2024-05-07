package me.shijunjiee.camundastudy.controller;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.shijunjiee.camundastudy.dto.StartProcessRequest;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ProcessController {

    @Resource
    private IdentityService identityService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @GetMapping("/start/{processKey}")
    public void startProcess(@PathVariable(value = "processKey") String processKey) {
        identityService.setAuthenticatedUserId("demo");

        VariableMap variables = Variables.createVariables();
        List<String> videoNames = new ArrayList<>();
        videoNames.add("孤岛危机");
        videoNames.add("战士5");
        variables.put("videoNames", videoNames);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, variables);
        log.info("startProcess result:{}", JSON.toJSONString(processInstance));
    }

    @PostMapping("/process/start")
    public String startProcess(@RequestBody StartProcessRequest startProcessRequest) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().tenantIdIn(startProcessRequest.getTenantId())
                .processDefinitionKey(startProcessRequest.getProcessKey())
                .processDefinitionVersion(Integer.parseInt(startProcessRequest.getVersion())).singleResult();
        if (processDefinition == null) {
            return "processDefinition is null";
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), startProcessRequest.getBusinessKey(), startProcessRequest.getVariables());
        return processInstance.getId();
    }

    @GetMapping("/sendMessage")
    public void sendMessage(@RequestParam("messageName") String messageName, @RequestParam(value = "businessKey", required = false) String businessKey) {
        VariableMap variables = Variables.createVariables();
        variables.put("result1", "this is result1");
        variables.put("result2", "this is result2");
        runtimeService.createMessageCorrelation(messageName)
//                .processInstanceBusinessKey(businessKey)
                .setVariables(variables).correlate();
    }

}
