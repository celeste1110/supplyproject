package com.supply.orders.models.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecivedOrderDto {
	
	@NotEmpty
	private String ratingReceived;
	
	private String commentReceived;
}
