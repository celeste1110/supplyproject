package com.supply.client.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmbarkationResponseDto {
	
private Long id;
	
	private String name;
	
	private double dailyFactor;
	
	private ClientDto client;
	

}
