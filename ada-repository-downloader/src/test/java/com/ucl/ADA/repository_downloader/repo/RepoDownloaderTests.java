package com.ucl.ADA.repository_downloader.repo;


import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RepoDownloaderTests {
    private RepoDbPopulator repository;

    @BeforeEach
    void initUseCase() throws GitAPIException {
        String branch = "";
        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";
        RepoDbPopulator repo = new RepoDbPopulator();
        repo.setUrl(url);
        repo.setBranch(branch);
        repository = RepoDownloader.downloadRepository(repo);
    }

    @Test
    void setupInitialisesRepository() {
        assert (repository.getUrl()).equals("https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git");
        assert (repository.getName().equals("Fitbit-JSON-Data-Generator"));
        assert (repository.getOwner().equals("sebastianvburlacu"));
        assert (repository.getBranch().equals("master"));
    }
}