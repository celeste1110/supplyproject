package com.supply.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supply.products.models.dto.MeasuringUnitDto;
import com.supply.products.services.MeasuringUnitService;

@RestController
@RequestMapping("/mea")
public class MeasuringUnitController {
	
	@Autowired
	MeasuringUnitService measuringUnitService;
	
	@GetMapping
	public ResponseEntity<List<MeasuringUnitDto>> listAll() {
		
		List<MeasuringUnitDto> listMeasuring=measuringUnitService.findMeasuringUnitAll();
		return new ResponseEntity<>(listMeasuring,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MeasuringUnitDto> detail(@PathVariable Long id) {
		MeasuringUnitDto measuring = measuringUnitService.finddMeasuringUnitById(id);
		
		return new ResponseEntity<>(measuring,HttpStatus.OK);
	}
	

}
