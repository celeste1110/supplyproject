package com.supply.client.models.dto;

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
public class EmbarkationRequestDto {
	
	private Long id;
	
	@NotEmpty(message = "name should not be null or empty")
	private String name;
	
	@NotNull(message = "daily Factor should not be null")
	private double dailyFactor;
	
	@NotEmpty(message = "code client should not be null or empty")
	private String cod_client;
	



}
