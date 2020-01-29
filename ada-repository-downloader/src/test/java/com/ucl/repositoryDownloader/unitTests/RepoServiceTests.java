package com.ucl.repositoryDownloader.unitTests;

import com.ucl.repostitoryDownloader.repo.Repo;
import com.ucl.repostitoryDownloader.repo.RepoService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RepoServiceTests {
    private Repo repository;
    private String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";

    @BeforeEach
    void initUseCase() throws GitAPIException {
        RepoService repoService = new RepoService();
        String branch = "";
        Repo repo = new Repo();
        repo.setUrl(url);
        repo.setBranch(branch);
        repoService.downloadRepository(repo);
        repository = repoService.repository;
    }

    @Test
    void setupInitialisesRepository() {
        assert (repository.getUrl()).equals(url);
        assert (repository.getName().equals("Fitbit-JSON-Data-Generator"));
        assert (repository.getOwner().equals("sebastianvburlacu"));
        assert (repository.getBranch().equals("master"));
    }
}
