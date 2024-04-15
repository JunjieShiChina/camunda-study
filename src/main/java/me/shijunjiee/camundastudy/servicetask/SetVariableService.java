package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SetVariableService {

    public void putVariable(DelegateExecution execution, String key, String value) {
        log.info("SetVariableService putVariable");
        execution.setVariable(key, value);
    }

}
