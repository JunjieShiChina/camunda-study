package me.shijunjiee.camundastudy.listeners;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component("setLeadersListener")
public class SetLeadersListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        ArrayList<String> leaders = new ArrayList<>();
        leaders.add("junjie");
        leaders.add("ronghao");
        leaders.add("zhangbo");
        delegateExecution.setVariable("leaders", leaders);
    }
}
