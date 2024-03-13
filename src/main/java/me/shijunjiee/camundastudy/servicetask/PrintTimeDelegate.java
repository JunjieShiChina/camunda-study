package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class PrintTimeDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("当前系统时间:{}", new Date());
    }
}
