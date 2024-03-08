package me.shijunjiee.camundastudy.servicetask;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TelCallService {

    public long doCall(DelegateExecution execution) {
        log.info("开始电话回访,execution:{}", JSON.toJSONString(execution.getVariables()));
        return 10;
    }

    public void getScore(DelegateExecution execution) {
        log.info("查看评分,execution:{}", JSON.toJSONString(execution.getVariables()));
        log.info("评分:{}", execution.getVariable("score"));
    }
}
