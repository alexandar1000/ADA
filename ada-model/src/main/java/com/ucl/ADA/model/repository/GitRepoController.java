package com.ucl.ADA.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class GitRepoController {

    @Autowired
    private GitRepoService gitRepoService;

    /**
     * Endpoint for listing all GitRepository entities in the DB.
     *
     * @return a List of all GitRepository entities.
     */
    @CrossOrigin
    @PostMapping("/repositories")
    public List<GitRepo> listAllRepositories() {
        return gitRepoService.listRepositories();
    }

    /**
     * Endpoint for listing the names of all GitRepositories stored in the DB.
     *
     * @return list of repository names
     */
    @CrossOrigin
    @PostMapping("/repositories/names")
    public List<String> listAllRepoNames() {
        return gitRepoService.listRepoNames();
    }

    /**
     * Endpoint for getting all repositories owned by a given owner
     * @param owner username of the owner
     * @return set of repositories owned by the owner
     */
    @CrossOrigin
    @PostMapping("/owners/{owner}/repositories")
    public Set<GitRepo> getAllReposForUser(@PathVariable String owner) {
        return gitRepoService.findAllReposByOwner(owner);
    }

    /**
     * Endpoint for getting a Git repository given its name and the username of its owner
     * @param owner username of the owner
     * @param repository name of the repository
     * @return the corresponding Git repository
     */
    @CrossOrigin
    @PostMapping("/owners/{owner}/repositories/{repository}")
    public GitRepo getRepoByOwnerAndName(@PathVariable String owner,
                                         @PathVariable String repository) {
        return gitRepoService.findRepoByOwnerAndRepoName(owner, repository);
    }
}
