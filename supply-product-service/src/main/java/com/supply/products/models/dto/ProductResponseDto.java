package com.supply.products.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

	private Long id;
	private String name;
	private double price;
	private CategoryDto category;
	private MeasuringUnitDto measuringUnit;
	
}
