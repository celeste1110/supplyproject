package com.supply.products.models.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeasuringUnitDto {

	private Long id;
	@NotEmpty
	private String nameMUnit;
	private String symbol;
	
}
