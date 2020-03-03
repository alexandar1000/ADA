package com.ucl.ADA.repository_downloader;


import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RepoDownloaderTests {

    @Test
    void testDownloadingRepositoryWithCorrectGitURL() throws GitAPIException {
        RepoDbPopulator populator;
        String branch = "";
        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";

        populator = RepoDownloader.downloadRepository(url, branch);

        assertEquals("sebastianvburlacu",populator.getOwner());
        assertEquals("master",populator.getBranch());
        assertEquals("Fitbit-JSON-Data-Generator",populator.getName());
    }

    @Test
    void testGitAPIExceptionThrownIfUrlIsWrong(){

        Exception exception = assertThrows(GitAPIException.class,()->{
            String branch = "";
            String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Gfsafsaenerator.git";
            RepoDbPopulator populator = RepoDownloader.downloadRepository(url, branch);
        });

    }
}