package com.supply.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SupplyOrdersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyOrdersServiceApplication.class, args);
	}

}
