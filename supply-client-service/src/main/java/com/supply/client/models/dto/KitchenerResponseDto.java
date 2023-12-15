package com.supply.client.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KitchenerResponseDto {
	
	private Long id;
	
	private String dni;
	
	private String name;
	
	private String lastname;
	
	private String fullName;
	
	private String phone;
	
	private EmbarkationResponseDto embarkation;
	


}
