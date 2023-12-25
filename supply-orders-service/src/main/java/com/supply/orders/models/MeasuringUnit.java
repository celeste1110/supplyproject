package com.supply.orders.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeasuringUnit {

	private Long id;
	private String nameMUnit;
	private String symbol;
}
