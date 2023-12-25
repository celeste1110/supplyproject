package com.supply.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SupplyConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyConfigServerApplication.class, args);
	}

}
