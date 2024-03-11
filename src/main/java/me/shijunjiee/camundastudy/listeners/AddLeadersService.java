package me.shijunjiee.camundastudy.listeners;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class AddLeadersService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("add leaders listener");
        ArrayList<String> leaders = new ArrayList<>();
        long leaveDays = (long) delegateExecution.getVariable("leaveDays");
        if (leaveDays == 3) {
            leaders.add("junjie");
            leaders.add("ronghao");
        } else if (leaveDays > 3) {
            leaders.add("junjie");
            leaders.add("ronghao");
            leaders.add("zhangbo");
        }
        delegateExecution.setVariable("leaders", leaders);
    }
}
