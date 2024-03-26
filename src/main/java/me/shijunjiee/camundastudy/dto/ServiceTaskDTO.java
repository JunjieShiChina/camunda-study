package me.shijunjiee.camundastudy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ServiceTaskDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String topic;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String delegateExpression;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String incoming;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String outgoing;

}
