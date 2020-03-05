package com.ucl.ADA.model.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BranchController {

    @Autowired
    private BranchService branchService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{user_name}/repositories/{repo_name}/branches")
    public Set<Branch> getBranchesByOwnerAndRepo(@PathVariable String user_name,
                                                 @PathVariable String repo_name) {

    }

    @GetMapping(value = "/{user_name}/repositories/{repo_name}/branches/{branch_name}")
    public Branch getBranchGivenOwnerRepoAndName(@PathVariable String user_name,
                                                 @PathVariable String repo_name,
                                                 @PathVariable String branch_name) {

    }
}
