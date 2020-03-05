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
    @GetMapping("/owners/{user_name}/repositories")
    public Set<GitRepository> getAllReposForUser(@PathVariable String user_name) {
        Set<GitRepository> gitRepositories = repoService.findAllReposByOwner(user_name);
        return gitRepositories;
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/owners/{user_name}/repositories/{repo_name}")
    public GitRepository getRepoByOwnerAndName(@PathVariable String user_name,
                                               @PathVariable String repo_name) {
        return repoService.findRepoByOwnerAndRepoName(user_name, repo_name);
    }
}
