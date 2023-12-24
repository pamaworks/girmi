package com.girmi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GirmiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GirmiGatewayApplication.class, args);
	}

}
