package com.ucl.ADA.repository_downloader.controllers;

import com.ucl.ADA.repository_downloader.entities.GitRepository;
import com.ucl.ADA.repository_downloader.entities.User;
import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
import com.ucl.ADA.repository_downloader.services.RepoService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller for storing the metadata of a given repository in the database.
 */

@RestController
@RequestMapping("/repositories")
public class RepoController {

    @Autowired private RepoService repoService;

    /**
     * Download a repository and populate the database with its metadata (owner, repoName, branch, timestamp, fileNames etc...)
     */

    //todo: delete this endpoint, not used

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/repo-metadata")
//    public User addEntry(@RequestBody RepoDbPopulator repo) {
//
//        User user;
//        try {
//            repoService.addEntry(repo);
//        }
//        catch (Exception exception) {
//            return null;
//        }
//        return user;
//    }

    @GetMapping
    public List<GitRepository> listAllRepositories(){
        return repoService.listRepositories();
    }

    @GetMapping("/names")
    public List<String> listAllRepoNames(){
        return repoService.listRepoNames();
    }
}
