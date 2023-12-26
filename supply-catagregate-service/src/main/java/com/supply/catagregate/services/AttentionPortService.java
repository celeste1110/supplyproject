package com.supply.catagregate.services;

import com.supply.catagregate.models.documents.dto.AttentionPortDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface AttentionPortService {

	public Flux<AttentionPortDto> findAPortAll();
	
	public Mono<AttentionPortDto> findAPortById(String id);
	
	public Mono<AttentionPortDto> saveAPort(AttentionPortDto port);
	
	public Mono<AttentionPortDto> updateAport(String id,AttentionPortDto port);
	
	public Mono<Void> deleteAport(String id);
}
