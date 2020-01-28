package com.ucl.ADA.integration;

import com.ucl.ADA.AdaApplication;
import com.ucl.ADA.metrics.MetricController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(args = "--spring.profiles.active=test")
class AdaApplicationTests {

	@Autowired
	private MetricController metricController;

	@Test
	void contextLoads() {
		assertThat(metricController).isNotNull();
	}

	@Test
	void homeTest() {
		AdaApplication adaApplication = new AdaApplication();
		String result = adaApplication.home();
		assertEquals(result, "Welcome to this wonderfully amazing webpage. Still a bit shy tho..");
	}
}
