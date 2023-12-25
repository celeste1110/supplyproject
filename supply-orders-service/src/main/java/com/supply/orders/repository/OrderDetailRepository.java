package com.supply.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supply.orders.models.entity.OrderDetailEntity;



public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>{
	
 List<OrderDetailEntity> findByCodOrder(Long codOrder);
 
}
