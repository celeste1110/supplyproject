package com.supply.catagregate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supply.catagregate.models.entity.AttentionPort;
import com.supply.catagregate.services.AttentionPortService;

@RestController
@RequestMapping("/attentionport")
public class AttentionPortController {
	
	@Autowired
	private AttentionPortService service;
	
	@GetMapping
	public ResponseEntity<List<AttentionPort>> listAll() {
		
		List<AttentionPort> aports=service.findAttentionPortAll();
		return new ResponseEntity<>(aports,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AttentionPort> detail(@PathVariable Long id) {
		AttentionPort aport = service.findAttentionPortById(id);
		
		return new ResponseEntity<>(aport ,HttpStatus.OK);
	}

}
