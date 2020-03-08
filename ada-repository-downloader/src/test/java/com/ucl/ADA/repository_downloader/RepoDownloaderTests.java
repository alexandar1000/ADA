package com.ucl.ADA.repository_downloader;


import com.ucl.ADA.model.project_structure.RepoDbPopulator;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RepoDownloaderTests {

    @AfterAll
    static void deleteDir(){
        String path = System.getProperty("user.dir")+"/temp";
        FileUtils.deleteQuietly(new File(path));
    }

    @Test
    void testDownloadingRepositoryWithCorrectGitURL() throws GitAPIException {
        RepoDbPopulator populator;
        String branch = "";
        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";

        populator = RepoDownloader.downloadRepository(url, branch);

        assertEquals("sebastianvburlacu",populator.getOwner());
        assertEquals("master",populator.getBranch());
        assertEquals("Fitbit-JSON-Data-Generator",populator.getRepository());
    }

    @Test
    void testGitAPIExceptionThrownIfUrlIsWrong(){

        Exception exception = assertThrows(GitAPIException.class,()->{
            String branch = "master";
            String url = "https://github.com/alexandar1000/ADA-test-simple-JAVasdfaA-project-0.git";
            RepoDbPopulator populator = RepoDownloader.downloadRepository(url, branch);
        });

    }
    @Test
    void testDownloadingRepositoryWithCorrectGitURLAndBranch() throws GitAPIException {
        RepoDbPopulator populator;
        String branch = "testing";
        String url = "https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0.git";

        populator = RepoDownloader.downloadRepository(url, branch);

        assertEquals("alexandar1000",populator.getOwner());
        assertEquals(branch,populator.getBranch());
        assertEquals("ADA-test-simple-JAVA-project-0",populator.getRepository());
    }

    @Test
    void testGitAPIExceptionThrownIfBranchNameIsNonExistent(){

        Exception exception = assertThrows(GitAPIException.class,()->{
            String branch = "not-existing-branch";
            String url = "https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0.git";
            RepoDbPopulator populator = RepoDownloader.downloadRepository(url, branch);
        });

    }
}