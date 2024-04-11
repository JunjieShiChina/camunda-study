package me.shijunjiee.camundastudy.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;

@Component
public class TagListener implements ExecutionListener {


    private FixedValue tag;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        System.out.println(tag.getExpressionText());
        execution.setVariableLocal(tag.getExpressionText(), tag.getExpressionText());
    }
}
