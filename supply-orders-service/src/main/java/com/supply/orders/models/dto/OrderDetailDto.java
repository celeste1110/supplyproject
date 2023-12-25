package com.supply.orders.models.dto;

import java.util.Objects;

import com.supply.orders.models.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDto {

	private Long id;
	
	private Long codOrder;

	private Product product;

	private Integer quantify;
	
	private String comment;

	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailDto other = (OrderDetailDto) obj;
		return Objects.equals(id, other.id);
	}

}
