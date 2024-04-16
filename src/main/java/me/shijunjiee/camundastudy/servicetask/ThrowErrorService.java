package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThrowErrorService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("ThrowErrorService");
        throw new RuntimeException("ThrowErrorService");
    }
}
