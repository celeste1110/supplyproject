package com.supply.orders.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supply.orders.models.dto.BuyOrderDto;
import com.supply.orders.models.dto.ConfirmationOrderDto;
import com.supply.orders.models.dto.DeliveryOrderDto;
import com.supply.orders.models.dto.FilterDto;
import com.supply.orders.models.dto.OrderDetailDto;
import com.supply.orders.models.dto.OrderDto;
import com.supply.orders.models.dto.OrderSingleDto;
import com.supply.orders.models.dto.RecivedOrderDto;
import com.supply.orders.models.entity.State;
import com.supply.orders.services.OrderService;

import jakarta.validation.Valid;


@RestController

public class OrderController {
	
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@GetMapping("/state")
	public ResponseEntity<List<State>> listStateAll(){
		
		List<State> listStstatus=orderService.findStateAll();
		
		return new ResponseEntity<>(listStstatus,HttpStatus.OK);
	}
	
	@GetMapping("/state/{id}")
	public ResponseEntity<State> getState(@PathVariable Long id){
		State status=orderService.findStateById(id);
		return new ResponseEntity<>(status,HttpStatus.OK);
		
	}
	
	//orders
	@PostMapping("/listPageable")
	public ResponseEntity<Page<OrderDto>> listOrderPageableAll(@RequestBody FilterDto filter,Pageable pageable) {
		
		Page<OrderDto> listOrders=orderService.findOrderByAllPageable(filter,pageable);
		return new ResponseEntity<>(listOrders,HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<List<OrderDto>> listOrderAll(@RequestBody FilterDto filter){
		
		List<OrderDto> listOrders=orderService.findOrderAll(filter);
		
		return new ResponseEntity<>(listOrders,HttpStatus.OK);
	}
	@PostMapping("/listOrderSingle")
	
	public ResponseEntity<List<OrderSingleDto>> listOrderSingleAll(@RequestBody FilterDto filter){
		
		List<OrderSingleDto> listOrders=orderService.findOrderSingleAll(filter);
		
		return new ResponseEntity<>(listOrders,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable Long id){
		OrderDto orders=orderService.findOrderDtoById(id);
		return new ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	
	@GetMapping("/orderSingle/{id}")
	public ResponseEntity<OrderSingleDto> getOrderSingle(@PathVariable Long id){
		OrderSingleDto orders=orderService.findOrderSingleDtoById(id);
		return new ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto order){
		
		return new ResponseEntity<>(orderService.saveOrder(order),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id){
		orderService.deleteOrder(id);
		 return new ResponseEntity<>("Order deleted",HttpStatus.OK);
	}
	
	@PutMapping("/confirmationOrder/{id}")
	public ResponseEntity<String> confirmationOrder(@PathVariable Long id, @RequestBody ConfirmationOrderDto order){
		orderService.confirmationOrder(id,order);
		 return new ResponseEntity<>("Order Confirmation success",HttpStatus.OK);
	}
	
	@PutMapping("/cancelOrder/{id}")
	public ResponseEntity<String> cancelOrder(@PathVariable Long id){
		orderService.cancelOrder(id);
		 return new ResponseEntity<>("Order Cancel success",HttpStatus.OK);
	}
	
	@PutMapping("/buyOrder/{id}")
	public ResponseEntity<String> buyOrder(@PathVariable Long id, @RequestBody BuyOrderDto order){
		orderService.buyOrder(id,order);
		 return new ResponseEntity<>("Order Buyed success",HttpStatus.OK);
	}
	
	@PutMapping("/deliveryOrder/{id}")
	public ResponseEntity<String> deliveryOrder(@PathVariable Long id, @RequestBody DeliveryOrderDto order){
		orderService.deliveryOrder(id,order);
		 return new ResponseEntity<>("Order delivery success",HttpStatus.OK);
	}
	
	@PutMapping("/receivedOrder/{id}")
	public ResponseEntity<String> receivedOrder(@PathVariable Long id, @RequestBody RecivedOrderDto order){
		orderService.recivedOrder(id,order);
		 return new ResponseEntity<>("Order received success",HttpStatus.OK);
	}
	
	@GetMapping("/orderDetail/{id}")
	public ResponseEntity<List<OrderDetailDto>> getOrderDetail(@PathVariable Long id){
		List<OrderDetailDto> orders=orderService.findByCodOrder(id);
		return new ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto order){
		OrderDto orderResponse=orderService.updateOrder(id,order);
		 return new ResponseEntity<>(orderResponse,HttpStatus.OK);
	}
	
	
	
	
	
	

}
