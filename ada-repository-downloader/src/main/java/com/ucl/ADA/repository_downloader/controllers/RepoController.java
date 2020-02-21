package com.ucl.ADA.repository_downloader.controllers;

import com.ucl.ADA.model.repository.GitRepository;
import com.ucl.ADA.repository_downloader.services.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepoController {

    @Autowired private RepoService repoService;

    /**
     * Endpoint for listing all GitRepository entities in the DB.
     * @return a List of all GitRepository entities.
     */
    @GetMapping
    public List<GitRepository> listAllRepositories(){
        return repoService.listRepositories();
    }

    /**
     * Endpoint for listing the names of all GitRepositories stored in the DB.
     * @return list of repository names
     */
    @GetMapping("/names")
    public List<String> listAllRepoNames(){
        return repoService.listRepoNames();
    }
}
