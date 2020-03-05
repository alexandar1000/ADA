package com.ucl.ADA.model.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/{user_name}/repositories/{repo_name}/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;


    @CrossOrigin("http://localhost:4200")
    @GetMapping
    public Set<Branch> getBranchesByOwnerAndRepo(@PathVariable String user_name,
                                                 @PathVariable String repo_name) {
        return branchService.getBranchesByOwnerAndRepo(user_name, repo_name);
    }

    @GetMapping("/{branch_name}")
    public Branch getBranchGivenOwnerRepoAndName(@PathVariable String user_name,
                                                 @PathVariable String repo_name,
                                                 @PathVariable String branch_name) {
        return branchService.getBranchGivenOwnerRepoAndName(user_name, repo_name, branch_name);
    }
}
