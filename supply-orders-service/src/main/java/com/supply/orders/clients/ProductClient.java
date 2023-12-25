package com.supply.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.supply.orders.models.Product;

@FeignClient(name="servicio-products")
public interface ProductClient {

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable(name="id") Long id);
}
