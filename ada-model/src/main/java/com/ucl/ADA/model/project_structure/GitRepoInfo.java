package com.ucl.ADA.model.project_structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * A helper class, created and initialized by the RepoDownloaderService and used to populate the database.
 * Contains metadata of the downloaded Repository.
 */

@Getter
@Setter
public class GitRepoInfo {

    /**
     * GitHub url
     */
    @JsonIgnore
    private String url;

    /**
     * owner or organization that owns the repo
     */
    private String owner;

    /**
     * repo name
     */
    private String repository;

    /**
     * branch name
     */
    private String branch;

    /**
     * time that ADA receives request for processing repo
     */
    private OffsetDateTime timestamp;

    /**
     * a list of file names
     */
    @JsonIgnore
    private List<String> fileNames = new ArrayList<>();

    /**
     * directory path where downloaded project is stored
     */
    @JsonIgnore
    private String directoryPath;

    /**
     * the snapshot project for connecting ProjectStructure
     */
    @JsonIgnore
    private Snapshot snapshot;
}
