package com.ucl.ADA;

import com.github.javafaker.Faker;
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
		Faker faker = new Faker();
		String name = faker.name().firstName();
		String country = faker.country().name();
		String yodaQuote = faker.yoda().quote();
		String pokemon = faker.pokemon().name();

		return "Hello " + name + "! As you are from " + country + ", we have a special Yoda quote for you: \""
+ yodaQuote + "\" Also, you are most similar to " + pokemon + "! :)";	}

}
