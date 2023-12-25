package com.supply.catagregate.services;

import com.supply.catagregate.models.documents.dto.SupplierDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SupplierService {
	
	public Flux<SupplierDto> findSupplierAll();
	
	public Mono<SupplierDto> findSupplierById(String id);
	
	public Mono<SupplierDto> saveSupplier(SupplierDto supplier);
	
	public Mono<SupplierDto> updateSupplier(String id,SupplierDto supplier);
	
	public Mono<Void> deleteSupplier(String id);

}
