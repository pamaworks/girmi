package com.girmi.data.mybatis;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class DataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataMybatisApplication.class, args);
	}

}
