package com.ucl.ADA.repository_downloader.repo;


import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RepoDownloaderTests {

    @Test
    void testDownloadingRepositoryWithCorrectGitURL() throws GitAPIException {
        RepoDbPopulator populator = new RepoDbPopulator();
        String branch = "";
        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";
        populator.setUrl(url);
        populator.setBranch(branch);

        populator = RepoDownloader.downloadRepository(populator);

        ArrayList<String> list = new ArrayList<>();
        assertEquals("sebastianvburlacu",populator.getOwner());
        assertEquals("master",populator.getBranch());
        assertEquals("Fitbit-JSON-Data-Generator",populator.getName());
    }

    @Test
    void testGitAPIExceptionThrownIfUrlIsNotGit(){

        Exception exception = assertThrows(GitAPIException.class,()->{
            RepoDbPopulator repoDbPopulator = new RepoDbPopulator();
            String branch = "";
            String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Gfsafsaenerator.git";
            repoDbPopulator.setUrl(url);
            repoDbPopulator.setBranch(branch);
            RepoDownloader.downloadRepository(repoDbPopulator);
        });

    }
}