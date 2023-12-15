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
import com.supply.client.models.dto.filter.FilterClientDto;
import com.supply.client.services.ClientService;


import jakarta.validation.Valid;

@RestController
//@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
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

}
