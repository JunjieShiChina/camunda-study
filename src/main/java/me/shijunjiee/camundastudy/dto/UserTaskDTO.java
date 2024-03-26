package me.shijunjiee.camundastudy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class UserTaskDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String assignee;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String incoming;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String outgoing;
}
