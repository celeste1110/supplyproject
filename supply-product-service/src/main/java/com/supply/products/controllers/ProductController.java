package com.supply.products.controllers;

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

import com.supply.products.models.dto.FilterProductDto;
import com.supply.products.models.dto.ProductRequestDto;
import com.supply.products.models.dto.ProductResponseDto;
import com.supply.products.services.ProductService;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<Page<ProductResponseDto>> listAllPageable(Pageable pageable) {
		
		Page<ProductResponseDto> listProduct=productService.findProductByAll(pageable);
		return new ResponseEntity<>(listProduct,HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ProductResponseDto>> listAll(){
		List<ProductResponseDto> listProducts=productService.findProductByAllDefault();
		
		return new ResponseEntity<>(listProducts,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
		ProductResponseDto product = productService.findProductById(id);
		
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<ProductResponseDto>> filter(@Valid @RequestBody FilterProductDto request) {
		
		List<ProductResponseDto> listProducts=productService.findProductByName(request);
		return new ResponseEntity<>(listProducts,HttpStatus.OK);
			
	}
	
	@PostMapping
	public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto product) {
		
		ProductResponseDto saveProduct=productService.saveProduct(product);
		return new ResponseEntity<>(saveProduct,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> editProduct(@Valid @RequestBody ProductRequestDto product,@PathVariable Long id) {
		
		ProductResponseDto editProduct=productService.updateProduct(product,id);
		return new ResponseEntity<>(editProduct,HttpStatus.OK);

	}

	@PutMapping("/{id}/disabled")
	public ResponseEntity<String> enabledProduct(@PathVariable Long id) {
		
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product succesfully deleted",HttpStatus.OK);
		
	}
	
	
	
	
	
	

}
