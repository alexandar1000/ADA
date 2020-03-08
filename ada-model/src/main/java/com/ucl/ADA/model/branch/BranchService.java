package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.repository.GitRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BranchService {

    @Autowired
    private GitRepoService gitRepoService;

    @Autowired
    private BranchRepository branchRepository;


    public Set<Branch> getBranchesByOwnerAndRepo(String user_name, String repo_name) {
        GitRepo gitRepo = gitRepoService.findRepoByOwnerAndRepoName(user_name, repo_name);

        return branchRepository.findAllByRepository(gitRepo);
    }

    public Branch getBranchGivenOwnerRepoAndName(String user_name, String repo_name, String branch_name) {
        GitRepo gitRepo = gitRepoService.findRepoByOwnerAndRepoName(user_name, repo_name);

        return branchRepository.findByRepositoryAndBranchName(gitRepo, branch_name);
    }
}
