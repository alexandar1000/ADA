package com.ucl.ADA.repo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;

import java.io.File;

public class GitDownloader {
    private static void downloadRepo(String url) throws GitAPIException {
        String clone_path = "";
        File file = new File(clone_path);
        try{
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(file)
                    .setCloneAllBranches(true)
                    .call();
        }
        catch (JGitInternalException e) {
            GitDownloader.deleteDirectory(file);
            GitDownloader.downloadRepo(url);
        }
    }

    static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
