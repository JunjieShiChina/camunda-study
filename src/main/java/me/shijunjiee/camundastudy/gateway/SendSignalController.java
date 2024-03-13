package me.shijunjiee.camundastudy.gateway;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SendSignalController {

    @Resource
    private RuntimeService runtimeService;

    @GetMapping("/sendSignal")
    public boolean sendSignal() {
        runtimeService.createSignalEvent("Signal_direct_leader").send();
        return true;
    }

}
