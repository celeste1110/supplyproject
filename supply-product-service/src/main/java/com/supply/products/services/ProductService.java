package com.supply.products.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.supply.products.models.dto.FilterProductDto;
import com.supply.products.models.dto.ProductRequestDto;
import com.supply.products.models.dto.ProductResponseDto;
import com.supply.products.models.entity.Product;

public interface ProductService {
	
	public Page<ProductResponseDto> findProductByAll(Pageable pageable);
	public ProductResponseDto findProductById(Long id);
	public List<ProductResponseDto> findProductByName(FilterProductDto request);
	public ProductResponseDto saveProduct(ProductRequestDto producto) ;
	public ProductResponseDto updateProduct(ProductRequestDto producto,Long id);
	public void deleteProduct(Long id);
	public Product findProductByIdGeneral(Long id);


}
