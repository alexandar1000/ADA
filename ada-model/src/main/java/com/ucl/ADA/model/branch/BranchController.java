package com.ucl.ADA.model.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("owners/{owner}/repositories/{repo_name}/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;


    @CrossOrigin("http://localhost:4200")
    @PostMapping
    public Set<Branch> getBranchesByOwnerAndRepo(@PathVariable String owner,
                                                 @PathVariable String repo_name) {
        return branchService.getBranchesByOwnerAndRepo(owner, repo_name);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/{branch_name}")
    public Branch getBranchGivenOwnerRepoAndName(@PathVariable String owner,
                                                 @PathVariable String repo_name,
                                                 @PathVariable String branch_name) {
        return branchService.getBranchGivenOwnerRepoAndName(owner, repo_name, branch_name);
    }
}
