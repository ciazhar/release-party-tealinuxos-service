package com.ciazhar.releasepartyservice.service;

import com.ciazhar.releasepartyservice.model.Agenda;
import com.ciazhar.releasepartyservice.model.request.CreateAgendaForm;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AgendaService{
    Flux<Agenda> all();
    Mono<Agenda> save(CreateAgendaForm form);
}