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

import com.supply.client.models.dto.EmbarkationResponseDto;
import com.supply.client.models.dto.KitchenerRequestDto;
import com.supply.client.models.dto.KitchenerResponseDto;
import com.supply.client.models.dto.filter.FilterKitchenerDto;
import com.supply.client.services.KitchenerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/kitcheners")
public class KitchenerController {
	
	@Autowired
	private KitchenerService kitchenerService;
	
	@GetMapping
	public ResponseEntity<Page<KitchenerResponseDto>> listAll(Pageable pageable) {
		
		Page<KitchenerResponseDto> listKitchener=kitchenerService.findKitchenerPageableByAll(pageable);
		return new ResponseEntity<>(listKitchener,HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<KitchenerResponseDto>> listAll(){
		List<KitchenerResponseDto> listKitchener=kitchenerService.findKitchenerByAll();
		
		return new ResponseEntity<>(listKitchener,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<KitchenerResponseDto> getKitchener(@PathVariable Long id) {
		KitchenerResponseDto kitchener = kitchenerService.findKitchenerById(id);
		
		return new ResponseEntity<>(kitchener,HttpStatus.OK);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<KitchenerResponseDto>> filter(@Valid @RequestBody FilterKitchenerDto request) {
		
		List<KitchenerResponseDto> listKitchener=kitchenerService.findKitchenerByNameAndDniAndEmbarcation(request);
		return new ResponseEntity<>(listKitchener,HttpStatus.OK);
			
	}
	
	@PostMapping
	public ResponseEntity<KitchenerResponseDto> createKitchener(@Valid @RequestBody KitchenerRequestDto kitchener) {
		
		KitchenerResponseDto saveKitchener=kitchenerService.saveKitchener(kitchener);
		return new ResponseEntity<>(saveKitchener,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<KitchenerResponseDto> editKitchener(@Valid @RequestBody KitchenerRequestDto kitchener,@PathVariable Long id) {
		
		KitchenerResponseDto editKitchener=kitchenerService.updateKitchener(kitchener,id);
		return new ResponseEntity<>(editKitchener,HttpStatus.OK);

	}

	@PutMapping("/{id}/disabled")
	public ResponseEntity<String> enabledKitchener(@PathVariable Long id) {
		
		kitchenerService.deleteKitchener(id);
		return new ResponseEntity<>("Kitchener succesfully deleted",HttpStatus.OK);
		
	}

}
