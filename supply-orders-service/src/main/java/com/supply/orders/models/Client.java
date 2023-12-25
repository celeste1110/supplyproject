package com.supply.orders.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
	
	private String id;
	
	private String ruc;
	
	private String socialReason;
	
	private String abbreviation;

}
