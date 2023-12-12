package com.supply.products.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.supply.products.models.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByName(String name);

	
	
	
}
