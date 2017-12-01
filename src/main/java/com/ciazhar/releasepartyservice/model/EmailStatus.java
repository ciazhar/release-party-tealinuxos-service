package com.ciazhar.releasepartyservice.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ciazhar on 01/12/17.
 * [ Documentatiion Here ]
 */

@Data
@Builder
public class EmailStatus {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    private String to;
    private String subject;
    private String body;

    private String status;
    private String errorMessage;
}
