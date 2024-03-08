package me.shijunjiee.camundastudy.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shijunjiee.camundastudy.service.GetAuditorService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class GetAuditorFromRestService implements GetAuditorService {

    @Override
    public List<String> getAssignees(String assignee1, String assignee2) {
        log.info("get assignees:{},{}", assignee1, assignee2);
        return Arrays.asList(assignee1, assignee2);
    }

}
