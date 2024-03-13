package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HandleOrderDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String endpoint = (String) execution.getVariable("endpoint");
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        if ("alipay".equals(endpoint)) {
            runtimeService.startProcessInstanceByMessage("Message_alipay", execution.getBusinessKey(), execution.getVariables());
        }
        if ("wechat".equals(endpoint)) {
            runtimeService.startProcessInstanceByMessage("Message_wechat_pay", execution.getBusinessKey(), execution.getVariables());
        }
    }
}
