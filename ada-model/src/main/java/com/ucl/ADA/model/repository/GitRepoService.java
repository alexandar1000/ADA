package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GitRepoService {

    @Autowired
    private GitRepoRepository gitRepoRepository;

    @Autowired
    private OwnerService ownerService;

    /**
     * Get all Git repositories in the database, sorted by ID in ascending order
     * @return a list of all Git repos
     */
    public List<GitRepo> listRepositories() {
        return gitRepoRepository.findAllByOrderByIdAsc();
    }

    /**
     * Get all Git repository names in the database
     * @return a list of all Git repos names
     */
    public List<String> listRepoNames() {
        return gitRepoRepository.fetchRepoNames();
    }

    /**
     * Get all Git repositories corresponding to a given Owner.
     * @param username of the owner
     * @return a set of Git repositories owned by the Owner
     */
    public Set<GitRepo> findAllReposByOwner(String username) {
        Owner owner = ownerService.getOwnerByName(username);

        return gitRepoRepository.findAllByOwner(owner);
    }

    /**
     * Get a Git repository with a given name and username of its owner
     * @param username of the owner
     * @param repository name of the Git repository
     * @return the corresponding Git repository
     */
    public GitRepo findRepoByOwnerAndRepoName(String username, String repository) {
        Owner owner = ownerService.getOwnerByName(username);

        return gitRepoRepository.findByOwnerAndRepoName(owner, repository);
    }

    /**
     * Add a GitRepo entity in the database.
     * @param repo GitRepo entity to be added
     * @return the newly added GitRepo entity
     */
    public GitRepo addGitRepo(GitRepo repo){
        return gitRepoRepository.save(repo);
    }

    public GitRepo getRepoByOwnerAndName(Owner owner, String name){
        return gitRepoRepository.findByOwnerAndRepoName(owner, name);
    }

}