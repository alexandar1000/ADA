package com.ucl.ADA.model.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("owners/{owner}/repositories/{repository}/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    /**
     * Endpoint for getting all branches given a Git repository and its owner
     * @param owner username of the owner
     * @param repository name of the Git repository
     * @return a set of all corresponding branches
     */
    @PostMapping
    public Set<Branch> getBranchesByOwnerAndRepo(@PathVariable String owner,
                                                 @PathVariable String repository) {
        return branchService.getBranchesByOwnerAndRepo(owner, repository);
    }

    /**
     * Endpoint for getting a branch entity given a Git repository, its owner and the name of the branch
     * @param owner name of the Git repository owner
     * @param repository name of the Git repository
     * @param branch name of the branch
     * @return the corresponding branch
     */
    @PostMapping("/{branch}")
    public Branch getBranchGivenOwnerRepoAndName(@PathVariable String owner,
                                                 @PathVariable String repository,
                                                 @PathVariable String branch) {
        return branchService.getBranchGivenOwnerRepoAndName(owner, repository, branch);
    }
}
