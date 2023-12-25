package com.supply.orders.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Embarkation {

private Long id;
	
	private String name;
	
	private double dailyFactor;
	
	private Client client;
}
