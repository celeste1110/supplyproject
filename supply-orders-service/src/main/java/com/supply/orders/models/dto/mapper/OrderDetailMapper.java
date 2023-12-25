package com.supply.orders.models.dto.mapper;

import com.supply.orders.models.dto.OrderDetailDto;
import com.supply.orders.models.entity.OrderDetailEntity;

public class OrderDetailMapper {
	
public static OrderDetailDto mapToOrderDetail(OrderDetailEntity orderDetailEntity) {
		
		return OrderDetailDto.builder()
				.id(orderDetailEntity.getId())
				.codOrder(orderDetailEntity.getCodOrder())
				.quantify(orderDetailEntity.getQuantify())
				.comment(orderDetailEntity.getComment())
				
				.build();
	}

}
