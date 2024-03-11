package me.shijunjiee.camundastudy.listeners;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component(value = "checkHomeAddressListener")
@Slf4j
public class CheckHomeAddressEndListener implements ExecutionListener {

    private static final String DefaultAddress = "用户注册地址";

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        log.info("检测用户是否填写地址");
        Object homeAddress = delegateExecution.getVariable("homeAddress");
        if (homeAddress == null) {
            delegateExecution.setVariable("homeAddress", DefaultAddress);
        }
    }
}
