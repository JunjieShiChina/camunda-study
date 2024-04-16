package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrintVariableDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("PrintVariableDelegate");
        execution.getVariables().forEach((k, v) -> log.info("key:{}, value:{}", k, v));
    }
}
