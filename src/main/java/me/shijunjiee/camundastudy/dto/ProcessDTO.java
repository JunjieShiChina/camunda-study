package me.shijunjiee.camundastudy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class ProcessDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isExecutable = true;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String historyTimeToLive;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StartEventDTO startEvent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EndEventDTO endEvent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SequenceFlowDTO> sequenceFlows;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ServiceTaskDTO> serviceTasks;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UserTaskDTO> userTasks;

}
