package com.ucl.ADA.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class RepoControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDownloadResponse() throws Exception {
        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";
        mvc.perform(MockMvcRequestBuilders.get("/analyse").param("url", url).accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
    }
}
