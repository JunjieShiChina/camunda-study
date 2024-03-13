package me.shijunjiee.camundastudy.servicetask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CheckVideoFormatDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("进入检测视频格式任务");
        Object videoName = execution.getVariable("videoName");
        log.info("检测到视频:{}", videoName);
    }
}
