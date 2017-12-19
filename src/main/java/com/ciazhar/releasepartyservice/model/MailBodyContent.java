package com.ciazhar.releasepartyservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by ciazhar on 16/12/17.
 * [ Documentatiion Here ]
 */

@Data
@Builder
public class MailBodyContent {
    private String username;
    private List<String> technology;
    private String message;
}
