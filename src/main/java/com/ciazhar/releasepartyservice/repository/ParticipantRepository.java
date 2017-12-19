package com.ciazhar.releasepartyservice.repository;

import com.ciazhar.releasepartyservice.model.Participant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by ciazhar on 01/12/17.
 */
public interface ParticipantRepository extends ReactiveMongoRepository<Participant,String> {
    Participant findByEmail(String email);
}
