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
import org.springframework.web.bind.annotation.RestController;

import com.supply.client.models.dto.ClientDto;
import com.supply.client.models.dto.EmbarkationRequestDto;
import com.supply.client.models.dto.EmbarkationResponseDto;
import com.supply.client.models.dto.KitchenerRequestDto;
import com.supply.client.models.dto.KitchenerResponseDto;
import com.supply.client.models.dto.filter.FilterClientDto;
import com.supply.client.models.dto.filter.FilterEmbarkationDto;
import com.supply.client.models.dto.filter.FilterKitchenerDto;
import com.supply.client.services.ClientService;
import com.supply.client.services.EmbarkationService;
import com.supply.client.services.KitchenerService;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmbarkationService embarkationService;
	
	@Autowired
	private KitchenerService kitchenerService;
	

	
	@GetMapping
	public ResponseEntity<Page<ClientDto>> listAll(Pageable pageable) {
		
		Page<ClientDto> listClient=clientService.findClientByAll(pageable);
		return new ResponseEntity<>(listClient,HttpStatus.OK);
	}
	@GetMapping("/list")
	public ResponseEntity<List<ClientDto>> listAll(){
		List<ClientDto> listClients=clientService.findClientByAllDefault();
		
		return new ResponseEntity<>(listClients,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> getClient(@PathVariable String id) {
		ClientDto client = clientService.findClientById(id);
		
		return new ResponseEntity<>(client,HttpStatus.OK);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<ClientDto>> filter(@Valid @RequestBody FilterClientDto request) {
		
		List<ClientDto> listClients=clientService.findClientByRucAndSocialReason(request);
		return new ResponseEntity<>(listClients,HttpStatus.OK);
			
	}
	
	@PostMapping
	public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto client) {
		
		ClientDto saveProduct=clientService.saveClient(client);
		return new ResponseEntity<>(saveProduct,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientDto> editClient(@Valid @RequestBody ClientDto client,@PathVariable String id) {
		
		ClientDto editClient=clientService.updateClient(client,id);
		return new ResponseEntity<>(editClient,HttpStatus.OK);

	}

	@PutMapping("/{id}/disabled")
	public ResponseEntity<String> enabledClient(@PathVariable String id) {
		
		clientService.deleteClient(id);
		return new ResponseEntity<>("Client succesfully deleted",HttpStatus.OK);
		
	}
	
	//embarcation
	@GetMapping("/embarkations")
	public ResponseEntity<Page<EmbarkationResponseDto>> listEmbarkationPageableAll(Pageable pageable) {
		
		Page<EmbarkationResponseDto> listEmbarkations=embarkationService.findEmbarkationPageableByAll(pageable);
		return new ResponseEntity<>(listEmbarkations,HttpStatus.OK);
	}
	@GetMapping("/embarkations/list")
	public ResponseEntity<List<EmbarkationResponseDto>> listEmbarkationAll(){
		List<EmbarkationResponseDto> listEmbarkations=embarkationService.findEmbarkationByAll();
		
		return new ResponseEntity<>(listEmbarkations,HttpStatus.OK);
	}

	@GetMapping("/embarkations/{id}")
	public ResponseEntity<EmbarkationResponseDto> getEmbarkation(@PathVariable Long id) {
		EmbarkationResponseDto embarkation = embarkationService.findEmbarkationById(id);
		
		return new ResponseEntity<>(embarkation,HttpStatus.OK);
	}
	
	@PostMapping("/embarkations/filter")
	public ResponseEntity<List<EmbarkationResponseDto>> filterEmbarkation(@Valid @RequestBody FilterEmbarkationDto request) {
		
		List<EmbarkationResponseDto> listEmbarkations=embarkationService.findEmbarkationByNameAndClientId(request);
		return new ResponseEntity<>(listEmbarkations,HttpStatus.OK);
			
	}
	
	@PostMapping("/embarkations")
	public ResponseEntity<EmbarkationResponseDto> createEmbarkation(@Valid @RequestBody EmbarkationRequestDto embarkation) {
		
		EmbarkationResponseDto saveEmbarkation=embarkationService.saveEmbarkation(embarkation);
		return new ResponseEntity<>(saveEmbarkation,HttpStatus.CREATED);
	}

	@PutMapping("/embarkations/{id}")
	public ResponseEntity<EmbarkationResponseDto> editEmbarkation(@Valid @RequestBody EmbarkationRequestDto embarkation,@PathVariable Long id) {
		
		EmbarkationResponseDto editEmbarkation=embarkationService.updateEmbarkation(embarkation,id);
		return new ResponseEntity<>(editEmbarkation,HttpStatus.OK);

	}

	@PutMapping("/embarkations/{id}/disabled")
	public ResponseEntity<String> enabledEmbarkation(@PathVariable Long id) {
		
		embarkationService.deleteEmbarkation(id);
		return new ResponseEntity<>("Embarkation succesfully deleted",HttpStatus.OK);
		
	}
	
	//kitchener
	
	@GetMapping("/kitcheners")
	public ResponseEntity<Page<KitchenerResponseDto>> listKitchenerPageableAll(Pageable pageable) {
		
		Page<KitchenerResponseDto> listKitchener=kitchenerService.findKitchenerPageableByAll(pageable);
		return new ResponseEntity<>(listKitchener,HttpStatus.OK);
	}
	
	@GetMapping("/kitcheners/list")
	public ResponseEntity<List<KitchenerResponseDto>> listKitchenerAll(){
		List<KitchenerResponseDto> listKitchener=kitchenerService.findKitchenerByAll();
		
		return new ResponseEntity<>(listKitchener,HttpStatus.OK);
	}

	@GetMapping("/kitcheners/{id}")
	public ResponseEntity<KitchenerResponseDto> getKitchener(@PathVariable Long id) {
		KitchenerResponseDto kitchener = kitchenerService.findKitchenerById(id);
		
		return new ResponseEntity<>(kitchener,HttpStatus.OK);
	}
	
	@PostMapping("/kitcheners/filter")
	public ResponseEntity<List<KitchenerResponseDto>> filterKitchener(@Valid @RequestBody FilterKitchenerDto request) {
		
		List<KitchenerResponseDto> listKitchener=kitchenerService.findKitchenerByNameAndDniAndEmbarcation(request);
		return new ResponseEntity<>(listKitchener,HttpStatus.OK);
			
	}
	
	@PostMapping("/kitcheners")
	public ResponseEntity<KitchenerResponseDto> createKitchener(@Valid @RequestBody KitchenerRequestDto kitchener) {
		
		KitchenerResponseDto saveKitchener=kitchenerService.saveKitchener(kitchener);
		return new ResponseEntity<>(saveKitchener,HttpStatus.CREATED);
	}

	@PutMapping("/kitcheners/{id}")
	public ResponseEntity<KitchenerResponseDto> editKitchener(@Valid @RequestBody KitchenerRequestDto kitchener,@PathVariable Long id) {
		
		KitchenerResponseDto editKitchener=kitchenerService.updateKitchener(kitchener,id);
		return new ResponseEntity<>(editKitchener,HttpStatus.OK);

	}

	@PutMapping("/kitcheners/{id}/disabled")
	public ResponseEntity<String> enabledKitchener(@PathVariable Long id) {
		
		kitchenerService.deleteKitchener(id);
		return new ResponseEntity<>("Kitchener succesfully deleted",HttpStatus.OK);
		
	}

}
