package com.ucl.repositoryDownloader.unitTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public class RepoControllerTests {

    @Autowired
    private MockMvc mvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
//
//    @Test
//    public void getDownloadResponse() throws Exception {
//        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";
//        String branch = "master";
//        Repo repo = new Repo();
//        repo.setUrl(url);
//        repo.setBranch(branch);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson=writer.writeValueAsString(repo);
//
//        mvc.perform(post("/download")
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.branch").value(branch));
//    }
}
