package com.aguilar.dhtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DucklingStoreApp {

	public static void main(String[] args) {
		SpringApplication.run(DucklingStoreApp.class, args);
	}

}
