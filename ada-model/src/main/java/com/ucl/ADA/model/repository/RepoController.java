package com.ucl.ADA.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RepoController {

    @Autowired
    private RepoService repoService;

    /**
     * Endpoint for listing all GitRepository entities in the DB.
     *
     * @return a List of all GitRepository entities.
     */
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/repositories")
    public List<GitRepository> listAllRepositories() {
        return repoService.listRepositories();
    }

    /**
     * Endpoint for listing the names of all GitRepositories stored in the DB.
     *
     * @return list of repository names
     */
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/repositories/names")
    public List<String> listAllRepoNames() {
        return repoService.listRepoNames();
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/owners/{owner}/repositories")
    public Set<GitRepository> getAllReposForUser(@PathVariable String owner) {
        Set<GitRepository> gitRepositories = repoService.findAllReposByOwner(owner);
        return gitRepositories;
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/owners/{owner}/repositories/{repository}")
    public GitRepository getRepoByOwnerAndName(@PathVariable String owner,
                                               @PathVariable String repository) {
        return repoService.findRepoByOwnerAndRepoName(owner, repository);
    }
}
