package me.shijunjiee.camundastudy.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class QueryController {

    @Resource
    private RuntimeService runtimeService;

    @GetMapping("/instance/execution/{processInstanceId}")
    public void testQuery(@PathVariable("processInstanceId") String processInstanceId) {
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstanceId);
        System.out.println();
    }

}
