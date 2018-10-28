package com.github.imtiazrahi.junit5mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Junit5MockApplication {

	public static void main(String[] args) {
		SpringApplication.run(Junit5MockApplication.class, args);
	}
}
