package com.supply.products.models.dto.mapper;

import com.supply.products.models.dto.ProductResponseDto;

import com.supply.products.models.entity.Product;

public class ProductMapper {
	
public static ProductResponseDto mapToProductDto(Product product) {
		
		ProductResponseDto productResponseoDto=new ProductResponseDto(
				
				product.getId(),
				product.getName(),
				product.getPrice(),
				CategoryMapper.mapToCategoryDto(product.getCategory()),
				MeasuringUnitMapper.mapToMeasuringUnitDto(product.getMeasuringUnit())
		
				);
		
		return productResponseoDto;
	}



}
