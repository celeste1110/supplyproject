package com.supply.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.supply.orders.models.Embarkation;
import com.supply.orders.models.Kitchener;

@FeignClient(name="servicio-clients")
public interface ClientsClient {	
	
	@GetMapping("/embarkations/{id}")
	public Embarkation getEmbarkation(@PathVariable(name="id") Long id);
	
	@GetMapping("/kitcheners/{id}")
	public Kitchener getKitchener(@PathVariable(name="id") Long id);
}
