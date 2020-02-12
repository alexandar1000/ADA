package com.ucl.ADA.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJpaRepositories(basePackages = "com.ucl.ADA.*")
@ComponentScan(basePackages = { "com.ucl.ADA.*" })
@EntityScan(basePackages = "com.ucl.ADA.*")
public class AdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdaApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Welcome to this wonderfully amazing webpage. Still a bit shy tho..";
	}

}
