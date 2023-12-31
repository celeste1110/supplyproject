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
public class CategoryDto {

	private Long codCategory;
	@NotEmpty(message = "name category should not be null or empty")
	private String nameCategory;

}
