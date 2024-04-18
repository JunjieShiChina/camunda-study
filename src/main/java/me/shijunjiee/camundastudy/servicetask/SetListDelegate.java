package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SetListDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("SetListDelegate");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        execution.setVariable("list", list);
    }

}
