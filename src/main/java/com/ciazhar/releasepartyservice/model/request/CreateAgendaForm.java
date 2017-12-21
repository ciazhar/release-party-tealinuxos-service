package com.ciazhar.releasepartyservice.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAgendaForm{
    private String agendaName;
}