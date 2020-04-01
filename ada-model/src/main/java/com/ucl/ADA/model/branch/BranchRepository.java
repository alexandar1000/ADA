package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.repository.GitRepo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long> {

    /**
     * Get all branches given a Git repository
     * @param gitRepo Git repository
     * @return set of branches corresponding to that Git repository
     */
    Set<Branch> findAllByRepository(GitRepo gitRepo);

    /**
     * Get a branch entity given a Git repository and branch name
     * @param gitRepo Git repository
     * @param branch name of branch
     * @return the corresponding branch entity
     */
    Branch findByRepositoryAndBranchName(GitRepo gitRepo, String branch);

}