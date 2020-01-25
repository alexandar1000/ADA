package com.ucl.ADA.repo;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RepoServicesTests {
    private Repo repository;
    private String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";

    @BeforeEach
    void initUseCase() throws GitAPIException {
        RepoServices repoServices = new RepoServices();
        String branch = "";
        repoServices.downloadRepository(url, branch);
        repository = repoServices.repository;
    }

    @Test
    void setupInitialisesRepository() {
        assert (repository.getUrl()).equals(url);
        assert (repository.getName().equals("Fitbit-JSON-Data-Generator"));
        assert (repository.getOwner().equals("sebastianvburlacu"));
        assert (repository.getBranch().equals("master"));
    }
}
