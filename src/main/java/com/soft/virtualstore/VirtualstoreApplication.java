package com.soft.virtualstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VirtualstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualstoreApplication.class, args);
	}
}