package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrintLeftDaysService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
      log.info("剩余假期为{}天", delegateExecution.getVariable("leftDay"));
    }
}
