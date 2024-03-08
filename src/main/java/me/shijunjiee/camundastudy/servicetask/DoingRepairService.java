package me.shijunjiee.camundastudy.servicetask;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DoingRepairService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("师傅上门修理,execution:{}", JSON.toJSONString(execution.getVariables()));
    }
}
