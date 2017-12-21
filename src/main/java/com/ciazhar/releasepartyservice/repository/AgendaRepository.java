package com.ciazhar.releasepartyservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ciazhar.releasepartyservice.model.Agenda;

public interface AgendaRepository extends ReactiveMongoRepository<Agenda,String>{
        
}