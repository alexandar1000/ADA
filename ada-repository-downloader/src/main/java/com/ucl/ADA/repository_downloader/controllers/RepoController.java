package com.ucl.ADA.repository_downloader.controllers;

import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
import com.ucl.ADA.repository_downloader.services.RepoService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for storing the metadata of a given repository in the database.
 */

@RestController
@RequestMapping("/repo-metadata")
@CrossOrigin(origins = "http://localhost:4200")
public class RepoController {

    @Autowired private RepoService repoService;

    /**
     * Download a repository and populate the database with its metadata (owner, repoName, branch, timestamp, fileNames etc...)
     */

    @PostMapping
    public void addEntry(@RequestBody RepoDbPopulator repo) throws GitAPIException {
        repoService.addEntry(repo);
    }
}
