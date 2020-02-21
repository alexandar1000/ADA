package com.ucl.ADA.repository_downloader.controllers;


import com.ucl.ADA.repository_downloader.entities.Branch;
import com.ucl.ADA.repository_downloader.entities.GitRepository;
import com.ucl.ADA.repository_downloader.entities.Owner;
import com.ucl.ADA.repository_downloader.entities.Snapshot;
import com.ucl.ADA.repository_downloader.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Controller for listing and deleting users/owners of Git repos in the database.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserService userService;

    @DeleteMapping
    public void deleteAll(){ userService.deleteAllUsers();}

    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable Long id){ userService.deleteUser(id);}


    @GetMapping(value = "/{user_name}")
    public Owner getUserByName(@PathVariable String user_name){
        return userService.getUserByName(user_name);
    }

    @GetMapping(value = "/{user_name}/repositories")
    public Set<GitRepository> getAllReposForUser(@PathVariable String user_name){
        return getUserByName(user_name).getRepos();
    }

    @GetMapping(value = "/{user_name}/repositories/{repo_name}")
    public GitRepository getRepoByOwnerAndName(@PathVariable String user_name,
                                               @PathVariable String repo_name){
        Set<GitRepository> repositories = getAllReposForUser(user_name);
        GitRepository repo = repositories.stream()
                .filter(r -> r.getRepoName().equals(repo_name)).findAny().orElse(null);

        return repo;
    }

    @GetMapping(value = "/{user_name}/repositories/{repo_name}/branches")
    public Set<Branch> getBranchesByOwnerAndRepo(@PathVariable String user_name,
                                                 @PathVariable String repo_name){
        GitRepository repo = getRepoByOwnerAndName(user_name,repo_name);
        return repo.getBranches();
    }

    @GetMapping(value = "/{user_name}/repositories/{repo_name}/branches/{branch_name}")
    public Branch getBranchGivenOwnerRepoAndName(@PathVariable String user_name,
                                                 @PathVariable String repo_name,
                                                 @PathVariable String branch_name){
        Set<Branch> branches = getBranchesByOwnerAndRepo(user_name,repo_name);
        Branch branch = branches.stream()
                .filter(b -> b.getBranchName().equals(branch_name)).findAny().orElse(null);

        return branch;
    }

    @GetMapping(value = "/{user_name}/repositories/{repo_name}/branches/{branch_name}/snapshots")
    public Set<Snapshot> getSnapshotsGivenOwnerRepoAndBranch(@PathVariable String user_name,
                                                             @PathVariable String repo_name,
                                                             @PathVariable String branch_name){
        Branch branch = getBranchGivenOwnerRepoAndName(user_name,repo_name,branch_name);
        return branch.getSnapshots();
    }

    // not sure if last parameter should be id or timestamp, set it as id for now

    @GetMapping(value = "/{user_name}/repositories/{repo_name}/branches/{branch_name}/snapshots/{id}")
    public Snapshot getSnapshotGivenOwnerRepoBranchAndId(@PathVariable String user_name,
                                                         @PathVariable String repo_name,
                                                         @PathVariable String branch_name,
                                                         @PathVariable Long id){

        Set<Snapshot> snapshots = getSnapshotsGivenOwnerRepoAndBranch(user_name,repo_name,branch_name);
        Snapshot snapshot = snapshots.stream()
                .filter(s -> s.getSnapshotID().equals(id)).findAny().orElse(null);

        return snapshot;
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Owner> listAllUsers() {
        return userService.listUsers();
    }

    @GetMapping("/names")
    public List<String> listAllUserNames(){
        return userService.listUserNames();
    }
}
