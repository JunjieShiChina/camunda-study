package me.shijunjiee.camundastudy.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class PrintVariableListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        execution.getVariables().forEach((k, v) -> System.out.println("key: " + k + ", value: " + v));
    }
}
