package com.supply.client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supply.client.models.dto.EmbarkationRequestDto;
import com.supply.client.models.dto.EmbarkationResponseDto;
import com.supply.client.models.dto.filter.FilterEmbarkationDto;
import com.supply.client.services.EmbarkationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/embarkations")
public class EmbarkationController {
	
	@Autowired
	private EmbarkationService embarkationService;
	
	@GetMapping
	public ResponseEntity<Page<EmbarkationResponseDto>> listAll(Pageable pageable) {
		
		Page<EmbarkationResponseDto> listEmbarkations=embarkationService.findEmbarkationPageableByAll(pageable);
		return new ResponseEntity<>(listEmbarkations,HttpStatus.OK);
	}
	@GetMapping("/list")
	public ResponseEntity<List<EmbarkationResponseDto>> listAll(){
		List<EmbarkationResponseDto> listEmbarkations=embarkationService.findEmbarkationByAll();
		
		return new ResponseEntity<>(listEmbarkations,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmbarkationResponseDto> getEmbarkation(@PathVariable Long id) {
		EmbarkationResponseDto embarkation = embarkationService.findEmbarkationById(id);
		
		return new ResponseEntity<>(embarkation,HttpStatus.OK);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<EmbarkationResponseDto>> filter(@Valid @RequestBody FilterEmbarkationDto request) {
		
		List<EmbarkationResponseDto> listEmbarkations=embarkationService.findEmbarkationByNameAndClientId(request);
		return new ResponseEntity<>(listEmbarkations,HttpStatus.OK);
			
	}
	
	@PostMapping
	public ResponseEntity<EmbarkationResponseDto> createEmbarkation(@Valid @RequestBody EmbarkationRequestDto embarkation) {
		
		EmbarkationResponseDto saveEmbarkation=embarkationService.saveEmbarkation(embarkation);
		return new ResponseEntity<>(saveEmbarkation,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmbarkationResponseDto> editEmbarkation(@Valid @RequestBody EmbarkationRequestDto embarkation,@PathVariable Long id) {
		
		EmbarkationResponseDto editEmbarkation=embarkationService.updateEmbarkation(embarkation,id);
		return new ResponseEntity<>(editEmbarkation,HttpStatus.OK);

	}

	@PutMapping("/{id}/disabled")
	public ResponseEntity<String> enabledEmbarkation(@PathVariable Long id) {
		
		embarkationService.deleteEmbarkation(id);
		return new ResponseEntity<>("Embarkation succesfully deleted",HttpStatus.OK);
		
	}
	

}
