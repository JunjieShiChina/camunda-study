package me.shijunjiee.camundastudy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class SequenceFlowDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sourceRef;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String targetRef;
}
