package com.supply.orders.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration

public class Configuration {
	
//	@Bean
//	public WebClient webClient() {
//		return WebClient.builder().build();
//	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClient() {
	    return WebClient.builder();
	}

	@Bean
	@Primary
	public WebClient.Builder webClient() {
	    return WebClient.builder();
	}

}
