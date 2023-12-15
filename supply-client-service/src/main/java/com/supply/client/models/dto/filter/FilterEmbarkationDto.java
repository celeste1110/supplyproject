package com.supply.client.models.dto.filter;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterEmbarkationDto {

	@NotEmpty
	private String codClient;
	
	@NotEmpty
	private String name;
}
