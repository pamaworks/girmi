package com.girmi.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@EnableFeignClients(basePackages = "com.girmi.jwt.clients")
@SpringBootApplication
public class LoginJwtApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(LoginJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] beans = applicationContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for(String bean : beans) {
			System.out.println(bean);
		}
	}
}
