package com.ciazhar.releasepartyservice.service;

import com.ciazhar.releasepartyservice.model.Participant;
import com.ciazhar.releasepartyservice.model.request.PaymentForm;
import com.ciazhar.releasepartyservice.model.request.RegisterForm;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by ciazhar on 01/12/17.
 */
public interface ParticipantService {
    Mono<Participant> register(RegisterForm form);
    Flux<Participant> findAll();
    Mono<Participant> pay(PaymentForm form);
    Mono<Void> sendReminder();
}
