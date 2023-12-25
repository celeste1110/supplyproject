package com.supply.orders.models.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterDto {
	
	private Long codState;
	private Long codEmbarkation;
	private LocalDate startDate;
	private LocalDate endDate;

}
