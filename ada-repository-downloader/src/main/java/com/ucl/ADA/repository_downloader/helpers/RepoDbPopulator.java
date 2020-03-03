package com.ucl.ADA.repository_downloader.helpers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * A helper class, created and initialized by the RepoDownloaderService and used to populate the database.
 * Contains metadata of the downloaded Repository.
 */

@Getter
@Setter
public class RepoDbPopulator {

    private String url;
    private String name;
    private String owner;
    private String branch;
    private List<String> fileNames = new ArrayList<>();
    private String directoryPath;

}
