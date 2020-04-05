package com.ucl.ADA.core;

import com.ucl.ADA.metric_calculator.metrics.MetricController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
class AdaApplicationTests {

	@Autowired
	private MetricController metricController;

	@Autowired
	private MockMvc mvc;

//	@Test
//	void contextLoads() {
//		assertThat(metricController).isNotNull();
//	}

	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);


//	@Test
//	void homeTest() {
//		AdaApplication adaApplication = new AdaApplication();
//		String result = adaApplication.home();
//		assertEquals(result, "Welcome to this wonderfully amazing webpage. Still a bit shy tho..");
//	}

//	@Test
//	 void getDownloadResponse() throws Exception {
//		String url = "https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0.git";
//		String branch = "master";
//		GitRepoInfo repoInfoUI = new GitRepoInfo();
//		repoInfoUI.setUrl(url);
//		repoInfoUI.setBranch(branch);
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson=writer.writeValueAsString(repoInfoUI);
//
//		mvc.perform(post("/repo-metadata")
//				.contentType(APPLICATION_JSON_UTF8)
//				.content(requestJson)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//	}
}
