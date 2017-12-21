package com.ciazhar.releasepartyservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class Agenda{
    @Id
    private String id;
    private String agendaName;
}