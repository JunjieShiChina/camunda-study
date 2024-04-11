package me.shijunjiee.camundastudy.listeners;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ManalTaskListener implements ExecutionListener {


    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info("Manual task listener");
        log.info(execution.getId());
        execution.getProcessEngine().getRuntimeService().suspendProcessInstanceById(execution.getProcessInstanceId());
    }
}
