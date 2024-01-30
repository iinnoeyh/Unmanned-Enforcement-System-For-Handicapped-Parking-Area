package com.example.systemmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA
@SpringBootApplication
public class SystemmanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemmanageApplication.class, args);
	}

}
