package me.shijunjiee.camundastudy.listeners;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component("goAddressListener")
@Slf4j
public class GoAddressStartListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        String homeAddress = (String) delegateExecution.getVariable("homeAddress");
        log.info("师傅前往{}进行修理", homeAddress);
    }
}
