package com.supply.users.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	private String id;
	
	private String ruc;

	private String socialReason;
	
	private String abbreviation;
	

}
