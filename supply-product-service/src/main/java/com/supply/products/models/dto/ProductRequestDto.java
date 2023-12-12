package com.supply.products.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {
	
	private Long id;
	@NotEmpty(message = "product name should not be null or empty")
	private String name;
	
	@NotNull(message = "price should not be null")
	private double  price;
	
	@NotNull(message = "category code should not be null")
	private Long codCategory;
	
	@NotNull(message = "measuring unit code  should not be null")
	private Long codMUnit;
	
	private Boolean activated;
	
}
