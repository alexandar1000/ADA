package com.ucl.repositoryDownloader.unitTests;


import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
import com.ucl.ADA.repository_downloader.helpers.RepoHelper;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RepoDownloaderTests {
    private RepoHelper repository;

    @BeforeEach
    void initUseCase() throws GitAPIException {
        String branch = "";
        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";
        RepoHelper repo = new RepoHelper();
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