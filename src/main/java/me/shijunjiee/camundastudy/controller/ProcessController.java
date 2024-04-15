package me.shijunjiee.camundastudy.controller;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ProcessController {

    @Resource
    private IdentityService identityService;

    @Resource
    private RuntimeService runtimeService;

    @GetMapping("/start/{processKey}")
    public void startProcess(@PathVariable(value = "processKey") String processKey) {
        identityService.setAuthenticatedUserId("demo");

        VariableMap variables = Variables.createVariables();
        List<String> videoNames = new ArrayList<>();
        videoNames.add("孤岛危机");
        videoNames.add("人狗情未了");
        videoNames.add("战士5");
        variables.put("videoNames", videoNames);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, variables);
        log.info("startProcess result:{}", JSON.toJSONString(processInstance));
    }

}
