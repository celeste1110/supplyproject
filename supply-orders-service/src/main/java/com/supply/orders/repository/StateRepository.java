package com.supply.orders.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supply.orders.models.entity.State;




public interface StateRepository extends JpaRepository<State, Long>{

	
	
}
