package me.shijunjiee.camundastudy.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class StartProcessRequest implements Serializable {

    private String processKey;
    private String version;
    private String tenantId;
    private HashMap<String, Object> variables;
    private String businessKey;

}
