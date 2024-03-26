package me.shijunjiee.camundastudy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class StartEventDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String initiator;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String outgoing;
}
