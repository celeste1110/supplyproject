package com.supply.orders.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.supply.orders.models.entity.OrderEntity;

import reactor.core.publisher.Mono;



public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	

	//@Query("SELECT  * FROM order  WHERE  (cod_state= :codState OR :codState=0 ) AND (cod_embarkation= :codEmbarkation OR :codEmbarkation=0) AND ((null= :startDate or null= :endDate) OR (CONVERT(DATE,orderDate,103) BETWEEN :startDate AND :endDate)) ORDER BY cod_order desc")
	


	
	
	
}
