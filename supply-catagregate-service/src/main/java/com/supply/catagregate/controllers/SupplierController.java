package com.supply.catagregate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supply.catagregate.models.entity.Supplier;
import com.supply.catagregate.services.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService service;
	
	@GetMapping
	public ResponseEntity<List<Supplier>> listAll() {
		
		List<Supplier> suppliers=service.findSupplierAll();
		return new ResponseEntity<>(suppliers,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Supplier> detail(@PathVariable Long id) {
		Supplier supplier = service.findSupplierPortById(id);
		
		return new ResponseEntity<>(supplier ,HttpStatus.OK);
	}

}
