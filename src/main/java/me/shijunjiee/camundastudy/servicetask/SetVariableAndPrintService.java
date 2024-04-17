package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SetVariableAndPrintService {

    public void putVariable(DelegateExecution execution, String key, String value) {
        log.info("SetVariableAndPrintService putVariable");
        execution.setVariable(key, value);
        // 打印所有变量
        execution.getVariables().forEach((k, v) -> log.info("key:{}, value:{}", k, v));
    }

}
