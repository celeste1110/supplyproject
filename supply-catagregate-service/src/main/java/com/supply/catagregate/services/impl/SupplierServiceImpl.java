package com.supply.catagregate.services.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.supply.catagregate.exceptions.ResourceNotFoundException;
import com.supply.catagregate.models.documents.Supplier;
import com.supply.catagregate.models.documents.dto.SupplierDto;
import com.supply.catagregate.models.documents.dto.mapper.SupplierDtoMapper;
import com.supply.catagregate.repository.SupplierRepository;
import com.supply.catagregate.services.SupplierService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SupplierServiceImpl implements SupplierService{
	

	private final SupplierRepository supplierRepo;
	
	public SupplierServiceImpl(SupplierRepository supplierRepo) {
		this.supplierRepo=supplierRepo;
	}

	
	@Override
	public Flux<SupplierDto> findSupplierAll() {
		return supplierRepo.findAll()
				.map(SupplierDtoMapper::mapToSupplierDto);
	}

	@Override
	public Mono<SupplierDto> findSupplierById(String id) {
		return supplierRepo.findById(id)
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Supplier not found with id: " + id)))
				.map(SupplierDtoMapper::mapToSupplierDto);
	}

	@Override
	public Mono<SupplierDto> saveSupplier(SupplierDto supplier) {
		return
				 Mono.just(new Supplier())
				
				.flatMap(p
						->{
					p.setId(supplier.getId());
					p.setName(supplier.getName());
					p.setCreatedBy("SHP-WEB");
					p.setCreationDate(LocalDate.now());
									
					return supplierRepo.save(p);
				})
				.switchIfEmpty(Mono.error(new Exception("There was an error, the Suppliercould not be created")))
				.map(SupplierDtoMapper::mapToSupplierDto);
	}

	@Override
	public Mono<SupplierDto> updateSupplier(String id, SupplierDto supplier) {
		return supplierRepo.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Not found Supplier with id " + id)))
				
				.flatMap(p->{
					
					p.setName(supplier.getName());
					p.setModifiedBy("SHP-WEB");
					p.setModificationDate(LocalDate.now());
					
					
					return supplierRepo.save(p);
				})
				.map(SupplierDtoMapper::mapToSupplierDto);
	}

	@Override
	public Mono<Void> deleteSupplier(String id) {
		return supplierRepo.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Not found Supplier with id " + id)))
				.flatMap(e->{
					return supplierRepo.deleteById(id);
				});
	}



	

}
