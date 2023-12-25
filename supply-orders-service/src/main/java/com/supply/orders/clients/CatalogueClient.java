package com.supply.orders.clients;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;

import com.supply.orders.models.AttentionPort;

import com.supply.orders.models.Supplier;


@Component
public class CatalogueClient {
	
	private final WebClient.Builder webClient;
	private final DiscoveryClient discoveryClient;
	
	public CatalogueClient (WebClient.Builder webClient,DiscoveryClient discoveryClient) {
		this.webClient=webClient;
		this.discoveryClient=discoveryClient;
	}
	
	private String url() {
		ServiceInstance serviceInstance=discoveryClient.getInstances("servicio-catagregate")
				.stream()
				.findFirst()
				.orElseThrow(()->new RuntimeException("service not found"));
		
		return serviceInstance.getUri().toString();
		
		
	}
	
	public AttentionPort getPort(String id) {
		
		return webClient.baseUrl(url())
				.build()
				.get()
				.uri("/attentionport/{id}",id)
				.retrieve()
				.bodyToMono(AttentionPort.class)
				.block();
	}

	public Supplier getSupplier(String id) {
		
		return webClient.baseUrl(url())
				.build()
				.get()
				.uri("/supplier/{id}",id)
				.retrieve()
				.bodyToMono(Supplier.class)
				.block();
	}
	
	
}
