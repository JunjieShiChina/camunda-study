package me.shijunjiee.camundastudy.servicetask;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrintService {

    public void doPrint(String value) {
        log.info("PrintService doPrint");
        log.info("value: {}", value);
    }

}
