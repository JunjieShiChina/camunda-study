package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import me.shijunjiee.camundastudy.dto.TestDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SetObjectVariableService {

    public void putVariable(DelegateExecution execution) {
        log.info("SetObjectVariableService putVariable");

        TestDTO testDTO = new TestDTO();
        testDTO.setName("testName");
        testDTO.setAge(18);
        execution.setVariable("testObject", testDTO);
    }

}
