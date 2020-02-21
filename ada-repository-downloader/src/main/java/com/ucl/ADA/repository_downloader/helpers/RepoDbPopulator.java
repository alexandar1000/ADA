package com.ucl.ADA.repository_downloader.helpers;

import java.util.ArrayList;
import java.util.List;


/**
 * A helper class, created and initialized by the RepoDownloaderService and used to populate the database.
 * Contains metadata of the downloaded Repository.
 */

public class RepoDbPopulator {

    private String url;
    private String name;
    private String owner;
    private String branch;
    private List<String> fileNames = new ArrayList<>();
    private String directoryPath;

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

}
