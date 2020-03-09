package com.ucl.ADA.model.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("owners/{owner}/repositories/{repository}/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;


    @CrossOrigin("http://localhost:4200")
    @PostMapping
    public Set<Branch> getBranchesByOwnerAndRepo(@PathVariable String owner,
                                                 @PathVariable String repository) {
        return branchService.getBranchesByOwnerAndRepo(owner, repository);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/{branch}")
    public Branch getBranchGivenOwnerRepoAndName(@PathVariable String owner,
                                                 @PathVariable String repository,
                                                 @PathVariable String branch) {
        return branchService.getBranchGivenOwnerRepoAndName(owner, repository, branch);
    }
}
