package com.ciazhar.releasepartyservice.controller;

import com.ciazhar.releasepartyservice.model.Participant;
import com.ciazhar.releasepartyservice.model.request.AttendForm;
import com.ciazhar.releasepartyservice.model.request.PaymentForm;
import com.ciazhar.releasepartyservice.model.request.RegisterForm;
import com.ciazhar.releasepartyservice.service.EmailService;
import com.ciazhar.releasepartyservice.service.ImageService;
import com.ciazhar.releasepartyservice.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static java.util.concurrent.TimeUnit.MINUTES;
import static org.springframework.http.CacheControl.maxAge;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Created by ciazhar on 01/12/17.
 * [ Documentatiion Here ]
 */

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

    private final ParticipantService service;
    private final EmailService emailService;
    private final ImageService imageService;

    @Autowired
    public ParticipantController(ParticipantService service, EmailService emailService, ImageService imageService) {
        this.service = service;
        this.emailService = emailService;
        this.imageService = imageService;
    }

    @PostMapping("/{agendaId}/register")
    public Mono<Participant> register(@RequestBody @Valid RegisterForm form,@PathVariable String agendaId){
        Participant participant = service.register(form,agendaId).block();
        emailService.sendEmail(participant);
        return Mono.just(participant);
    }

    private static final long THIRTY_MINUTES = 1800000;

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCode(@RequestParam String text){
        try {
            int QRCODE_WIDTH = 256;
            int QRCODE_HEIGHT = 256;
            return ok().cacheControl(maxAge(30, MINUTES))
                    .body(imageService.generateQRCodeAsync(text, QRCODE_WIDTH, QRCODE_HEIGHT).get());
        } catch (Exception e) {
            throw new RuntimeException("Error while generating QR code images.",e);
        }
    }

    @Scheduled(fixedRate = THIRTY_MINUTES)
    @DeleteMapping()
    @ResponseStatus(NO_CONTENT)
    public void deleteAllCachedImage(){
        imageService.purgeCache();
    }

    @GetMapping("/all")
    public Flux<Participant> findAll(){
        return service.findAll();
    }

    @GetMapping("/{agendaId}/all")
    public Flux<Participant> findByAgenda(@PathVariable String agendaId){
        return service.findByAgenda(agendaId);
    }
    
    @GetMapping("/pay")
    public Mono<Participant> pay(@RequestParam String id){
        return service.pay(
            PaymentForm.builder().participantId(id).build()
        );
    }

    @GetMapping("/reminder")
    public Mono<Void> sendReminder(){
        return service.sendReminder();
    }

    @GetMapping("/attend")
    public Mono<Participant> attend(@RequestParam String id){
        return service.attend(
            AttendForm.builder().participantId(id).build()
        );
    }
}
