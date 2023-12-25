package com.supply.orders.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kitchener {
	
	private Long id;
	
	private String dni;
	
	private String name;
	
	private String lastname;
	
	private String fullName;
	
	private String phone;

}
