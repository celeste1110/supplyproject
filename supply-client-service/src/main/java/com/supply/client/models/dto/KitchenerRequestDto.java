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
public class KitchenerRequestDto {

	private Long id;
	@NotEmpty(message = "dni should not be null or empty")
	private String dni;
	
	@NotEmpty(message = "name should not be null or empty")
	private String name;
	
	@NotEmpty(message = "lastname should not be null or empty")
	private String lastname;	
	
	private String phone;
	
	@NotNull(message = "embarkation code  should not be null")
	private Long cod_embarkation;
	
	
}
