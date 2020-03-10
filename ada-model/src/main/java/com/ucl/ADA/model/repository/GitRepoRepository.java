package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.owner.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GitRepoRepository extends CrudRepository<GitRepo, Long> {

    /**
     * Get all Git repository names in the database
     * @return a list of all Git repos names
     */
    @Query(value = "SELECT repo_name FROM public.repository ORDER BY owner_id ASC", nativeQuery = true)
    List<String> fetchRepoNames();

    /**
     * Get all Git repositories in the database, sorted by ID in ascending order
     * @return a list of all Git repos
     */
    List<GitRepo> findAllByOrderByIdAsc();

    /**
     * Get all Git repositories corresponding to a given owner.
     * @param owner Git repo owner entity
     * @return a set of Git repositories owned by the owner
     */
    Set<GitRepo> findAllByOwner(Owner owner);

    /**
     * Get a Git repository with a given name and username of its owner
     * @param owner Git repo owner entity
     * @param repoName name of the Git repository
     * @return the corresponding Git repository
     */
    GitRepo findByOwnerAndRepoName(Owner owner, String repoName);
}
