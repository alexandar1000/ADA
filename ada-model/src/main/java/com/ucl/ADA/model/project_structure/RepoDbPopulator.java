package com.ucl.ADA.model.project_structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * A helper class, created and initialized by the RepoDownloaderService and used to populate the database.
 * Contains metadata of the downloaded Repository.
 */

@Getter
@Setter
public class RepoDbPopulator {

    @JsonIgnore
    private String url;

    private String owner;

    private String repository;

    private String branch;

    private LocalDateTime timestamp;

    @JsonIgnore
    private List<String> fileNames = new ArrayList<>();

    @JsonIgnore
    private String directoryPath;

    @JsonIgnore
    private Snapshot snapshot;
}
