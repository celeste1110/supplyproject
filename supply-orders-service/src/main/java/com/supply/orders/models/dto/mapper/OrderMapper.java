package com.supply.orders.models.dto.mapper;

import com.supply.orders.models.dto.OrderDto;
import com.supply.orders.models.dto.OrderSingleDto;
import com.supply.orders.models.entity.OrderEntity;

public class OrderMapper {
	
	public static OrderDto mapToOrderDto(OrderEntity orderEntity) {
		
		return OrderDto.builder()
				.id(orderEntity.getId())
				.quantifyDays(orderEntity.getQuantifyDays())
				.numberCrew(orderEntity.getNumberCrew())
				.nroSolped(orderEntity.getNroSolped())
				.orderDate(orderEntity.getOrderDate())
				.sailDate(orderEntity.getSailDate())
				.state(orderEntity.getState())
				.comment(orderEntity.getComment())
				.confirmationDate(orderEntity.getConfirmationDate())
				.purchaseDate(orderEntity.getPurchaseDate())
				.deliveyDate(orderEntity.getDeliveyDate())
				.commentDelivery(orderEntity.getCommentDelivery())
				.receivedDate(orderEntity.getReceivedDate())
				.ratingReceived(orderEntity.getRatingReceived())
				.commentReceived(orderEntity.getCommentReceived())
				.spendAmount(orderEntity.getSpendAmount())
				
				.build();
	}
	
public static OrderSingleDto mapToOrderSingleDto(OrderEntity orderEntity) {
		
		return OrderSingleDto.builder()
				.id(orderEntity.getId())
				.quantifyDays(orderEntity.getQuantifyDays())
				.numberCrew(orderEntity.getNumberCrew())
				.nroSolped(orderEntity.getNroSolped())
				.orderDate(orderEntity.getOrderDate())
				.sailDate(orderEntity.getSailDate())
				.state(orderEntity.getState())
				.comment(orderEntity.getComment())
				.confirmationDate(orderEntity.getConfirmationDate())
				.purchaseDate(orderEntity.getPurchaseDate())
				.deliveyDate(orderEntity.getDeliveyDate())
				.commentDelivery(orderEntity.getCommentDelivery())
				.receivedDate(orderEntity.getReceivedDate())
				.ratingReceived(orderEntity.getRatingReceived())
				.commentReceived(orderEntity.getCommentReceived())
				.spendAmount(orderEntity.getSpendAmount())
				
				.build();
	}

public static OrderEntity mapToOrderEntity(OrderDto orderDto) {
	
	return OrderEntity.builder()
			.id(orderDto.getId())
			.quantifyDays(orderDto.getQuantifyDays())
			.numberCrew(orderDto.getNumberCrew())
			.sailDate(orderDto.getSailDate())
			.state(orderDto.getState())
			.codKitchener(orderDto.getKitchener().getId())
			.nameKitchener(orderDto.getKitchener().getFullName())
			.codEmbarkation(orderDto.getEmbarkation().getId())
			.codSupplier(orderDto.getSupplier().getId())
			.codAport(orderDto.getAttentionPort().getId())
			.comment(orderDto.getComment())
			
						.build();
}
	


}
