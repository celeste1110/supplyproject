package com.supply.catagregate.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supply.catagregate.models.documents.dto.AttentionPortDto;
import com.supply.catagregate.models.documents.dto.SupplierDto;
import com.supply.catagregate.services.AttentionPortService;
import com.supply.catagregate.services.SupplierService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/attentionport")
public class CatalogueController {
	
	
	private AttentionPortService service;
	
	private SupplierService serviceSupplier;
	
	public CatalogueController(AttentionPortService service,SupplierService serviceSupplier) {
		this.service=service;
		this.serviceSupplier=serviceSupplier;
	}
	
	
	//attention port

	@GetMapping("/attentionport")
	public ResponseEntity<Flux<AttentionPortDto>> listAttentionPortAll(){
		
		Flux<AttentionPortDto> listPorts=service.findAPortAll();
		
		return new ResponseEntity<>(listPorts,HttpStatus.OK);
	}
	
	@GetMapping("/attentionport/{id}")
	public ResponseEntity<Mono<AttentionPortDto>> getPort(@PathVariable String id){
		Mono<AttentionPortDto> port=service.findAPortById(id);
		return new ResponseEntity<>(port,HttpStatus.OK);
		
	}
	
	@PostMapping("/attentionport")
	public ResponseEntity<Mono<AttentionPortDto>> createPort(@Valid @RequestBody AttentionPortDto port){
		
		return new ResponseEntity<>(service.saveAPort(port),HttpStatus.CREATED);
	}
	
	@PutMapping( "/attentionport/{id}")
	   	public ResponseEntity<Mono<AttentionPortDto> >updatePort(@PathVariable String id,@Valid @RequestBody AttentionPortDto port) {
		
	       return new ResponseEntity<>(service.updateAport(id,port),HttpStatus.OK);
	    }
	
	@DeleteMapping("/attentionport/{id}")
	public ResponseEntity<Mono<Void>> deletePort(@PathVariable String id){
		
		 return new ResponseEntity<>(service.deleteAport(id),HttpStatus.NO_CONTENT);
	}
	
	//supplier
	
	@GetMapping("/supplier")
	public ResponseEntity<Flux<SupplierDto>> listSupplierAll(){
		
		Flux<SupplierDto> listSuppliers=serviceSupplier.findSupplierAll();
		
		return new ResponseEntity<>(listSuppliers,HttpStatus.OK);
	}
	
	@GetMapping("/supplier/{id}")
	public ResponseEntity<Mono<SupplierDto>> getSupplier(@PathVariable String id){
		Mono<SupplierDto> supplier=serviceSupplier.findSupplierById(id);
		return new ResponseEntity<>(supplier,HttpStatus.OK);
		
	}
	
	@PostMapping("/supplier")
	public ResponseEntity<Mono<SupplierDto>> createSupplier(@Valid @RequestBody SupplierDto supplier){
		
		return new ResponseEntity<>(serviceSupplier.saveSupplier(supplier),HttpStatus.CREATED);
	}
	
	@PutMapping( "/supplier/{id}")
	   	public ResponseEntity<Mono<SupplierDto> >updateSupplier(@PathVariable String id,@Valid @RequestBody SupplierDto supplier) {
		
	       return new ResponseEntity<>(serviceSupplier.updateSupplier(id,supplier),HttpStatus.OK);
	    }
	
	@DeleteMapping("/supplier/{id}")
	public ResponseEntity<Mono<Void>> deleteSupplier(@PathVariable String id){
		
		 return new ResponseEntity<>(serviceSupplier.deleteSupplier(id),HttpStatus.NO_CONTENT);
	}

}
