package com.supply.client.models.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

	private String id;
	@NotEmpty(message = "ruc should not be null or empty")
	private String ruc;
	
	@NotEmpty(message = "social reason should not be null or empty")
	private String socialReason;
	
	private String abbreviation;
	
}
