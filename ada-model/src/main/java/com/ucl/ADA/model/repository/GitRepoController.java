package com.ucl.ADA.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GitRepoController {

    @Autowired
    private GitRepoService gitRepoService;

    /**
     * Endpoint for listing all GitRepository entities in the DB.
     *
     * @return a List of all GitRepository entities.
     */
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/repositories")
    public List<GitRepo> listAllRepositories() {
        return gitRepoService.listRepositories();
    }

    /**
     * Endpoint for listing the names of all GitRepositories stored in the DB.
     *
     * @return list of repository names
     */
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/repositories/names")
    public List<String> listAllRepoNames() {
        return gitRepoService.listRepoNames();
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/owners/{owner}/repositories")
    public Set<GitRepo> getAllReposForUser(@PathVariable String owner) {
        return gitRepoService.findAllReposByOwner(owner);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/owners/{owner}/repositories/{repository}")
    public GitRepo getRepoByOwnerAndName(@PathVariable String owner,
                                         @PathVariable String repository) {
        return gitRepoService.findRepoByOwnerAndRepoName(owner, repository);
    }
}
