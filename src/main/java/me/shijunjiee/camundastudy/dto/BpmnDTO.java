package me.shijunjiee.camundastudy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class BpmnDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProcessDTO process;

}
