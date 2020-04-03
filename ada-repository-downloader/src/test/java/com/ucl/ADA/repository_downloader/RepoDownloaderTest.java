package com.ucl.ADA.repository_downloader;


import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


public class RepoDownloaderTest {

    @AfterAll
    static void deleteDir(){
        String path = System.getProperty("user.dir")+"/temp";
        FileUtils.deleteQuietly(new File(path));
    }

    @Test
    void testDownloadingRepositoryWithCorrectGitURL() throws GitAPIException {
        String branch = "";
        String url = "https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0.git";

        String rootPath = RepoDownloader.downloadRepository(url, branch);
        assertTrue(new File(rootPath).isDirectory());
        assertTrue(rootPath.contains("master"));
        assertTrue(rootPath.contains("ADA-test-simple-JAVA-project-0"));
        assertTrue(rootPath.contains("alexandar1000"));
    }

    @Test
    void testGitAPIExceptionThrownIfUrlIsWrong(){

        Exception exception = assertThrows(GitAPIException.class,()->{
            String branch = "master";
            String url = "https://github.com/alexandar1000/ADA-test-simple-JAVasdfaA-project-0.git";
            RepoDownloader.downloadRepository(url, branch);
        });

    }
    @Test
    void testDownloadingRepositoryWithCorrectGitURLAndBranch() throws GitAPIException {

        String branch = "testing";
        String url = "https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0.git";

        String rootPath = RepoDownloader.downloadRepository(url, branch);

        assertTrue(new File(rootPath).isDirectory());
        assertTrue(rootPath.contains("testing"));
        assertTrue(rootPath.contains("ADA-test-simple-JAVA-project-0"));
        assertTrue(rootPath.contains("alexandar1000"));
    }

    @Test
    void testGitAPIExceptionThrownIfBranchNameIsNonExistent(){

        Exception exception = assertThrows(GitAPIException.class,()->{
            String branch = "not-existing-branch";
            String url = "https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0.git";
            String rootPath = RepoDownloader.downloadRepository(url, branch);
        });

    }
}