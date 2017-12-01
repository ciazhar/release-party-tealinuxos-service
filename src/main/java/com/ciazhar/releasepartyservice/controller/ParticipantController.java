package com.ciazhar.releasepartyservice.controller;

import com.ciazhar.releasepartyservice.model.Participant;
import com.ciazhar.releasepartyservice.model.request.PaymentForm;
import com.ciazhar.releasepartyservice.model.request.RegisterForm;
import com.ciazhar.releasepartyservice.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Created by ciazhar on 01/12/17.
 * [ Documentatiion Here ]
 */

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

    @Autowired private ParticipantService service;

    @PostMapping("/register")
    public Mono<Participant> register(@RequestBody @Valid RegisterForm form){
        return service.register(form);
    }

    @GetMapping("/all")
    public Flux<Participant> findAll(){
        return service.findAll();
    }

    @PostMapping("/pay")
    public Mono<Participant> pay(@RequestParam String id){
        return service.pay(
            PaymentForm.builder().participantId(id).build()
        );
    }

    @GetMapping("/reminder")
    public Mono<Void> sendReminder(){
        return service.sendReminder();
    }
}
