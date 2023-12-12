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
public class FilterProductDto {

	@NotEmpty
	private String name;
	
	public FilterProductDto exampleBuild() {
		FilterProductDto prequest=new FilterProductDto();
		prequest.setName("");
		
		return prequest;
	}
	
}
