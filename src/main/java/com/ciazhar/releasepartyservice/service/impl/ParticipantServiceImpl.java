package com.ciazhar.releasepartyservice.service.impl;

import com.ciazhar.releasepartyservice.model.Participant;
import com.ciazhar.releasepartyservice.model.request.AttendForm;
import com.ciazhar.releasepartyservice.model.request.PaymentForm;
import com.ciazhar.releasepartyservice.model.request.RegisterForm;
import com.ciazhar.releasepartyservice.repository.ParticipantRepository;
import com.ciazhar.releasepartyservice.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by ciazhar on 01/12/17.
 */

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired private ParticipantRepository participantRepository;

    @Override
    public Mono<Participant> register(RegisterForm form) {
        return participantRepository.save(
            Participant.builder()
                .name(form.getName())
                .phoneNumber(form.getPhoneNumber())
                .email(form.getPhoneNumber())
                .jobStatus(form.getStatus())
                .dvdBitBit(form.getDvdBit())
                .build()
        );
    }

    @Override
    public Flux<Participant> findAll() {
        return participantRepository.findAll();
    }

    @Override
    public Mono<Participant> pay(PaymentForm form) {
        return participantRepository.findById(form.getParticipantId()).flatMap(
            participant -> setStatus(participant).flatMap(
                participant1 -> participantRepository.save(participant1)
            )
        );
    }

    private Mono<Participant> setStatus(Participant participant){
        participant.setPaymentStatus(true);
        return Mono.just(participant);
    }

    @Override
    public Mono<Void> sendReminder() {
        return null;
    }

    @Override
    public Mono<Participant> attend(AttendForm form) {
        return participantRepository.findById(form.getParticipantId()).flatMap(
            participant -> setAttendanceStatus(participant).flatMap(
                participant1 -> participantRepository.save(participant1)
            )
        );
    }

    private Mono<Participant> setAttendanceStatus(Participant participant){
        participant.setAttendanceStatus(true);
        return Mono.just(participant);
    }
}
