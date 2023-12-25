package com.supply.orders.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.supply.orders.models.dto.BuyOrderDto;
import com.supply.orders.models.dto.ConfirmationOrderDto;
import com.supply.orders.models.dto.DeliveryOrderDto;
import com.supply.orders.models.dto.FilterDto;
import com.supply.orders.models.dto.OrderDetailDto;
import com.supply.orders.models.dto.OrderDto;
import com.supply.orders.models.dto.OrderSingleDto;
import com.supply.orders.models.dto.RecivedOrderDto;
import com.supply.orders.models.entity.State;


public interface OrderService {
	
	//status
	
	public List<State> findStateAll();
	
	public State findStateById(Long id);
	
	
	// orders
	public Page<OrderDto> findOrderByAllPageable(FilterDto request,Pageable pageable);
	
	public List<OrderDto> findOrderAll(FilterDto requeste);
	
	public List<OrderSingleDto> findOrderSingleAll(FilterDto request);
	
	public OrderDto findOrderDtoById(Long id);
	
	public OrderSingleDto findOrderSingleDtoById(Long id);
	
	public OrderDto saveOrder(OrderDto order);
	
	public OrderDto updateOrder(Long id,OrderDto order);
	
	public void confirmationOrder(Long id,ConfirmationOrderDto order);
	
	public void cancelOrder(Long id);
	
	public void buyOrder(Long id,BuyOrderDto order);
	
	public void deliveryOrder(Long id,DeliveryOrderDto order);
	
	public void recivedOrder(Long id,RecivedOrderDto order);
	
	public void deleteOrder(Long id);
	
	//orderDetail
	
	
	

	
	public void saveOrderDetail(OrderDetailDto order);

	
	public List<OrderDetailDto> findByCodOrder(Long codOrder);

}
