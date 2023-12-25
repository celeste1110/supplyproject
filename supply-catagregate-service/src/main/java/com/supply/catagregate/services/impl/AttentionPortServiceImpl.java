package com.supply.catagregate.services.impl;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

import com.supply.catagregate.exceptions.ResourceNotFoundException;
import com.supply.catagregate.models.documents.AttentionPort;
import com.supply.catagregate.models.documents.dto.AttentionPortDto;
import com.supply.catagregate.models.documents.dto.mapper.AttentionPortMapper;
import com.supply.catagregate.repository.AttentionPortRepository;
import com.supply.catagregate.services.AttentionPortService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AttentionPortServiceImpl implements AttentionPortService{
	
	
	private final AttentionPortRepository arepository;
	
	public AttentionPortServiceImpl(AttentionPortRepository arepository) {
		this.arepository=arepository;
	}
	

	@Override
	public Flux<AttentionPortDto> findAPortAll() {
		return arepository.findAll()
				.map(AttentionPortMapper::mapToAttentionPortDto);
	}

	@Override
	public Mono<AttentionPortDto> findAPortById(String id) {
		return arepository.findById(id)
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Attention Port not found with id: " + id)))
				.map(AttentionPortMapper::mapToAttentionPortDto);
	}

	@Override
	public Mono<AttentionPortDto> saveAPort(AttentionPortDto port) {
		
		return
				 Mono.just(new AttentionPort())
				
				.flatMap(p
						->{
					p.setId(port.getId());
					p.setName(port.getName());
					p.setCreatedBy("SHP-WEB");
					p.setCreationDate(LocalDate.now());
									
					return arepository.save(p);
				})
				.switchIfEmpty(Mono.error(new Exception("There was an error, the Attention Port could not be created")))
				.map(AttentionPortMapper::mapToAttentionPortDto);
	}

	@Override
	public Mono<AttentionPortDto> updateAport(String id, AttentionPortDto port) {
		
		return arepository.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Not found Attention Port with id " + id)))
				
				.flatMap(p->{
					
					p.setName(port.getName());
					p.setModifiedBy("SHP-WEB");
					p.setModificationDate(LocalDate.now());
					
					
					return arepository.save(p);
				})
				.map(AttentionPortMapper::mapToAttentionPortDto);
	}

	@Override
	public Mono<Void> deleteAport(String id) {
		return arepository.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Not found Attention Port with id " + id)))
				.flatMap(e->{
					return arepository.deleteById(id);
				});
	}

}
