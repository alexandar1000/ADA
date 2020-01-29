package com.ucl.repositoryDownloader.unitTests;

import com.ucl.ADA.repository_downloader.repo.Repo;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RepoServiceTests {
    private Repo repository;
    private String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";

//    @BeforeEach
//    void initUseCase() throws GitAPIException {
//        RepoService repoService = new RepoService();
//        String branch = "";
//        Repo repo = new Repo();
//        repo.setUrl(url);
//        repo.setBranch(branch);
//        repoService.downloadRepository(repo);
//        repository = repoService.repository;
//    }
//
//    @Test
//    void setupInitialisesRepository() {
//        assert (repository.getUrl()).equals(url);
//        assert (repository.getName().equals("Fitbit-JSON-Data-Generator"));
//        assert (repository.getOwner().equals("sebastianvburlacu"));
//        assert (repository.getBranch().equals("master"));
//    }
}
