package com.supply.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SupplyEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyEurekaServerApplication.class, args);
	}

}
