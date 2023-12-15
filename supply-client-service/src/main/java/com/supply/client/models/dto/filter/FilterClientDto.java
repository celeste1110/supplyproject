package com.supply.client.models.dto.filter;


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
public class FilterClientDto {

	@NotNull
	private Integer type;
	
	@NotEmpty
	private String data;
	
}
