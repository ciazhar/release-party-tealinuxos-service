package com.ciazhar.releasepartyservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciazhar.releasepartyservice.model.Agenda;
import com.ciazhar.releasepartyservice.model.request.CreateAgendaForm;
import com.ciazhar.releasepartyservice.service.AgendaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController{

    @Autowired 
    AgendaService service;

    @GetMapping("/all")
    public Flux<Agenda> allAgenda(){
        return service.all();
    }

    @PostMapping("/save")
    public Mono<Agenda> saveAgenda(@Valid @RequestBody CreateAgendaForm form){
        return service.save(form);
    }

}