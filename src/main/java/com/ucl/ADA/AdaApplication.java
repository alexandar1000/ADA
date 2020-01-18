package com.ucl.ADA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class 	AdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdaApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Welcome to this wonderfully amazing webpage. Still a bit shy tho..";
	}

}
