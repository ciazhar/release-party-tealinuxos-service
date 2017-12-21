package com.ciazhar.releasepartyservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.ciazhar.releasepartyservice.model.Agenda;
import com.ciazhar.releasepartyservice.model.request.CreateAgendaForm;
import com.ciazhar.releasepartyservice.repository.AgendaRepository;
import com.ciazhar.releasepartyservice.service.AgendaService;

@Service
public class AgendaServiceImpl implements AgendaService{

    @Autowired 
    AgendaRepository agendaRepository;

	@Override
	public Flux<Agenda> all() {
		return agendaRepository.findAll();
	}

	@Override
	public Mono<Agenda> save(CreateAgendaForm form) {
		return agendaRepository.save(Agenda.builder()
			.agendaName(form.getAgendaName())
			.build()
		);
	}    

}