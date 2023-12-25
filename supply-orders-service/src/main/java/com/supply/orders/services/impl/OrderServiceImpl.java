package com.supply.orders.services.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.supply.orders.clients.CatalogueClient;
import com.supply.orders.clients.ClientsClient;
import com.supply.orders.clients.ProductClient;
import com.supply.orders.exceptions.BadRequestException;
import com.supply.orders.exceptions.ResourceNotFoundException;
import com.supply.orders.models.AttentionPort;
import com.supply.orders.models.Embarkation;
import com.supply.orders.models.Kitchener;
import com.supply.orders.models.Product;
import com.supply.orders.models.Supplier;
import com.supply.orders.models.dto.BuyOrderDto;
import com.supply.orders.models.dto.ConfirmationOrderDto;
import com.supply.orders.models.dto.DeliveryOrderDto;
import com.supply.orders.models.dto.FilterDto;
import com.supply.orders.models.dto.OrderDetailDto;
import com.supply.orders.models.dto.OrderDto;
import com.supply.orders.models.dto.OrderSingleDto;
import com.supply.orders.models.dto.RecivedOrderDto;
import com.supply.orders.models.dto.mapper.OrderDetailMapper;
import com.supply.orders.models.dto.mapper.OrderMapper;
import com.supply.orders.models.entity.OrderDetailEntity;
import com.supply.orders.models.entity.OrderEntity;
import com.supply.orders.models.entity.State;
import com.supply.orders.repository.OrderDetailRepository;
import com.supply.orders.repository.OrderRepository;
import com.supply.orders.repository.StateRepository;
import com.supply.orders.services.OrderService;
import static com.supply.orders.utils.StatesEnum.*;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final static String NF_STATE_MESSAGE="state not found with id ";
	private final static String BR_CONFIRMATION_MESSAGE="The order must be in requested status to be confirmed.";
	private final static String BR_BUY_MESSAGE="The order must be in confirmed status to be purchased.";
	private final static String BR_DELIVERY_MESSAGE="The order must be in purchased status to be delivered.";
	private final static String BR_RECEIVED_MESSAGE="The order must be in delivered status to confirm receipt.";
	
	private final StateRepository stateRepository;
	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final ClientsClient clientsClient;
	private final CatalogueClient catalogueClient;
	private final ProductClient productClient;

	
	public OrderServiceImpl(StateRepository stateRepository,OrderRepository orderRepository,OrderDetailRepository orderDetailRepository,
			ClientsClient clientsClient,CatalogueClient catalogueClient,ProductClient productClient
			)
	{
		this.stateRepository=stateRepository;
		this.orderRepository=orderRepository;
		this.orderDetailRepository=orderDetailRepository;
		this.clientsClient=clientsClient;
		this.catalogueClient=catalogueClient;
		this.productClient=productClient;
	}
	
	//state

	@Override
	public List<State> findStateAll() {
		
		return stateRepository.findAll();
	}

	@Override
	public State findStateById(Long id) {
		
		return stateRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("state not found with id: " + id));
	}
	
	//order
	
	@Override
	public Page<OrderDto> findOrderByAllPageable(FilterDto request, Pageable pageable) {
		
		List<OrderDto> listOrder=listOrderFilter(request)
				.stream()
				.map(p->{
					OrderDto order=setOrderEntity(p);
					return order;
					
				}).toList();
		
		if (listOrder.isEmpty()) {
			throw new ResourceNotFoundException("Page not found " + pageable);
		}
		
		return new PageImpl<>(listOrder, pageable, listOrder.size());
	}

	@Override
	public List<OrderDto> findOrderAll(FilterDto request) {
		
		return listOrderFilter(request)
				.stream()
				.map(p->{
					OrderDto order=setOrderEntity(p);
					return order;
					
				}).toList();
	}
	

	@Override
	public List<OrderSingleDto> findOrderSingleAll(FilterDto request) {

		return listOrderFilter(request)
				.stream()	
		.map(p->{
			OrderSingleDto order=setOrderSingleEntity(p);
			
			return order;
			
		}).toList();
	}

	@Override
	public OrderDto findOrderDtoById(Long id) {
		
		OrderEntity orderbd = findOrderEntityById(id);
		
		OrderDto order=setOrderEntity(orderbd);

		return order;
	}

	@Override
	public OrderSingleDto findOrderSingleDtoById(Long id) {
	
		OrderEntity orderbd = findOrderEntityById(id);
		
		OrderSingleDto order=setOrderSingleEntity(orderbd);

		return order;
		
	}
	
	@Override
	public OrderDto saveOrder(OrderDto order) {
		OrderEntity orderEntity=OrderMapper.mapToOrderEntity(order);
		State state=State.builder().id(SOLICITADO.getCodState())
				.description(SOLICITADO.name())
				.build();
		orderEntity.setState(state);
		orderEntity.setOrderDate(LocalDateTime.now());
			
		orderEntity= orderRepository.save(orderEntity);
		saveListOrderDetail(order.getOrderDetail(),orderEntity.getId());
		
		OrderDto orderResponse=setOrderEntity(orderEntity);
		
		return orderResponse;
				
	}
	@Override
	public OrderDto updateOrder(Long id, OrderDto order) {
		
		OrderEntity orderbd = findOrderEntityById(id);
		orderbd.setQuantifyDays(order.getQuantifyDays());
		orderbd.setNumberCrew(order.getNumberCrew());
		orderbd.setSailDate(order.getSailDate());
		orderbd.setCodSupplier(order.getSupplier().getId());
		orderbd.setCodAport(order.getAttentionPort().getId());
		orderbd.setComment(order.getComment());
		
		orderbd= orderRepository.save(orderbd);
		
		updateOrderDetail(order.getOrderDetail(),id);
		
		OrderDto orderResponse=setOrderEntity(orderbd);
		return orderResponse;
	}
	
	@Override
	public void deleteOrder(Long id) {
		
		OrderEntity orderbd = findOrderEntityById(id);
		orderRepository.delete(orderbd);
		List<OrderDetailDto> listDetail=findByCodOrder(id);
		
		listDetail.forEach(p->{
			orderDetailRepository.deleteById(p.getId());
		});
		
	}
	
	@Override
	public void confirmationOrder(Long id, ConfirmationOrderDto order) {
		
		OrderEntity orderbd = findOrderEntityById(id);
		
		if(orderbd.getState().getId()!=SOLICITADO.getCodState()) {
			
			throw new BadRequestException(BR_CONFIRMATION_MESSAGE);
			
		}
		State state=State.builder().id(PENDIENTE.getCodState())
				.description(PENDIENTE.name())
				.build();
		orderbd.setState(state);
		orderbd.setNroSolped(order.getNroSolped());
		orderbd.setConfirmationDate(LocalDateTime.now());
		
		orderbd=orderRepository.save(orderbd);
		
	
	}

	@Override
	public void cancelOrder(Long id) {
		
		OrderEntity orderbd = findOrderEntityById(id);
		
		State state=State.builder().id(ANULADO.getCodState())
				.description(ANULADO.name())
				.build();
		orderbd.setState(state);
		
		orderbd=orderRepository.save(orderbd);
	}

	@Override
	public void buyOrder(Long id, BuyOrderDto order) {
		
		OrderEntity orderbd = findOrderEntityById(id);
		
		if(orderbd.getState().getId()!=PENDIENTE.getCodState()) {
			
			throw new BadRequestException(BR_BUY_MESSAGE);
			
		}
		
		State state=State.builder().id(COMPRADO.getCodState())
				.description(COMPRADO.name())
				.build();
		orderbd.setState(state);
		orderbd.setPurchaseDate(LocalDateTime.now());
		orderbd.setSpendAmount(order.getSpendAmount());
		
		orderbd=orderRepository.save(orderbd);
		
	}

	@Override
	public void deliveryOrder(Long id, DeliveryOrderDto order) {
		
		OrderEntity orderbd = findOrderEntityById(id);
		
		if(orderbd.getState().getId()!=COMPRADO.getCodState()) {
			
			throw new BadRequestException(BR_DELIVERY_MESSAGE);
			
		}
		
		State state=State.builder().id(ENTREGADO.getCodState())
				.description(ENTREGADO.name())
				.build();
		orderbd.setState(state);
		orderbd.setDeliveyDate(LocalDateTime.now());
		orderbd.setCommentDelivery(order.getCommentDelivery());
		
		orderbd=orderRepository.save(orderbd);
		
	}

	@Override
	public void recivedOrder(Long id, RecivedOrderDto order) {
		
		OrderEntity orderbd = findOrderEntityById(id);
		
		if(orderbd.getState().getId()!=ENTREGADO.getCodState()) {
			
			throw new BadRequestException(BR_RECEIVED_MESSAGE);
			
		}
		
		State state=State.builder().id(RECIBIDO.getCodState())
				.description(RECIBIDO.name())
				.build();
		orderbd.setState(state);
		orderbd.setReceivedDate(LocalDateTime.now());
		orderbd.setCommentReceived(order.getCommentReceived());
		orderbd.setRatingReceived(order.getRatingReceived());
		
		orderbd=orderRepository.save(orderbd);
	}

	public List<OrderEntity> listOrderFilter(FilterDto request){
		
		return  orderRepository.findAll()
				.stream()
				.filter(p->(request.getCodState().equals(p.getState().getId())|| request.getCodState()==0) && 
						(request.getCodEmbarkation()==p.getCodEmbarkation() || request.getCodEmbarkation()==0) &&
						((null==request.getStartDate() || null==request.getEndDate() ) ||((p.getOrderDate().toLocalDate().isAfter(request.getStartDate()) || p.getOrderDate().toLocalDate().isEqual(request.getStartDate())) && 
								(p.getOrderDate().toLocalDate().isBefore(request.getEndDate()) || p.getOrderDate().toLocalDate().isEqual(request.getEndDate()))) )
						
						)
				.sorted(Comparator.comparing(OrderEntity::getOrderDate).reversed())
				.toList();
	}
	
	public OrderEntity findOrderEntityById(Long id) {
		
		OrderEntity orderbd = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

		return orderbd;
	}

	

	@Override
	public void saveOrderDetail(OrderDetailDto order) {
		
		OrderDetailEntity orderDetailEntity=new OrderDetailEntity();
		
		orderDetailEntity.setId(order.getId());
		orderDetailEntity.setCodOrder(order.getCodOrder());
		orderDetailEntity.setCodProduct(order.getProduct().getId());
		orderDetailEntity.setQuantify(order.getQuantify());
		orderDetailEntity.setComment(order.getComment());
		orderDetailRepository.save(orderDetailEntity);
	}



	@Override
	public List<OrderDetailDto> findByCodOrder(Long codOrder) {
		
		return orderDetailRepository.findByCodOrder(codOrder)
				.stream()
				.map(p->{
					OrderDetailDto orderDetail=OrderDetailMapper.mapToOrderDetail(p);
					Product product=productClient.getProduct(p.getCodProduct());
					orderDetail.setProduct(product);
					return orderDetail;
				}).toList();
	}
	
	public OrderDto setOrderEntity(OrderEntity orderEntity) {
		
		OrderDto order=OrderMapper.mapToOrderDto(orderEntity);
		Embarkation embarkation = clientsClient.getEmbarkation(orderEntity.getCodEmbarkation());
		Kitchener kitchener=clientsClient.getKitchener(orderEntity.getCodKitchener());
		AttentionPort aport=catalogueClient.getPort(orderEntity.getCodAport());
		Supplier supplier=catalogueClient.getSupplier(orderEntity.getCodSupplier());
		order.setEmbarkation(embarkation);
		order.setKitchener(kitchener);
		order.setAttentionPort(aport);
		order.setSupplier(supplier);
		order.setOrderDetail(this.findByCodOrder(orderEntity.getId()));
		
		return order;
	}
	
	public OrderSingleDto setOrderSingleEntity(OrderEntity orderEntity) {
		
		OrderSingleDto order=OrderMapper.mapToOrderSingleDto(orderEntity);
		Embarkation embarkation = clientsClient.getEmbarkation(orderEntity.getCodEmbarkation());
		Kitchener kitchener=clientsClient.getKitchener(orderEntity.getCodKitchener());
		AttentionPort aport=catalogueClient.getPort(orderEntity.getCodAport());
		Supplier supplier=catalogueClient.getSupplier(orderEntity.getCodSupplier());
		order.setEmbarkation(embarkation);
		order.setKitchener(kitchener);
		order.setAttentionPort(aport);
		order.setSupplier(supplier);
		
		
		return order;
	}
	
	public void saveListOrderDetail(List<OrderDetailDto> list,Long codOrder){
		
		list.forEach(p->{
			OrderDetailEntity order=new OrderDetailEntity();
			order.setId(p.getId());
			order.setCodOrder(codOrder);
			order.setCodProduct(p.getProduct().getId());
			order.setQuantify(p.getQuantify());
			order.setComment(p.getComment());
			orderDetailRepository.save(order);
			
		});
	}
	
	public void updateOrderDetail(List<OrderDetailDto> list,Long codOrder){
		
		List<OrderDetailDto> listDetail=findByCodOrder(codOrder);
		
		List<OrderDetailDto> listDif=new ArrayList<>(listDetail);
		listDif.removeAll(list);
		
		
		
		list.forEach(p->{
			
			if(p.getId()!=null) {
				
				Optional<OrderDetailEntity> orderbd = orderDetailRepository.findById(p.getId());
				
				if(orderbd.isPresent()) {
					
					OrderDetailEntity order=orderbd.get();
					order.setQuantify(p.getQuantify());
					order.setComment(p.getComment());
					orderDetailRepository.save(order);
				}
				
			}else {
				p.setCodOrder(codOrder);
				saveOrderDetail(p);
				
			}
			

			
		});
		
		listDif.forEach(e->{
			orderDetailRepository.deleteById(e.getId());
		});
	}


}
