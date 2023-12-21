package com.supply.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.supply.users.models.Client;

@FeignClient(name="servicio-clients")
public interface ClientClients {
	
	@GetMapping("/{id}")
	public Client getClient(@PathVariable(name = "id") String id);

}
