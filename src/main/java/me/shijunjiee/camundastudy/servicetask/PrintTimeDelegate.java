package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
@Slf4j
public class PrintTimeDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.getVariables().forEach((k, v) -> log.info("key:{}, value:{}", k, v));

        execution.setVariable("aaa", "asdfadwsf");
        log.info("当前系统时间:{}", new Date());

        // 获取扩展属性
        ServiceTask serviceTask = (ServiceTask) execution.getBpmnModelElementInstance();
        ExtensionElements extensionElements = serviceTask.getExtensionElements();
        Collection<CamundaProperty> camundaProperties = extensionElements.getElementsQuery().filterByType(CamundaProperties.class).singleResult().getCamundaProperties();
        camundaProperties.forEach(camundaProperty -> log.info("key:{}, value:{}", camundaProperty.getCamundaName(), camundaProperty.getCamundaValue()));

        System.out.println("PrintTimeDelegate");
    }
}
