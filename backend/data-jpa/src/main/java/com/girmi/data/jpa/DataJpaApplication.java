package com.girmi.data.jpa;

import com.girmi.data.jpa.common.CommonRepositoryImpl;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableEncryptableProperties
@EnableJpaRepositories(repositoryBaseClass = CommonRepositoryImpl.class)
@EntityScan(basePackages = {"com.girmi.models", "com.girmi.data.jpa.models"})
public class DataJpaApplication {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {

		SpringApplication.run(DataJpaApplication.class, args);

	}

}
