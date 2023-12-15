package com.supply.products.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supply.products.models.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select p from Product p where p.name=?1 and p.activated=true")
	List<Product> findByName(String name);

	
	
	
}
