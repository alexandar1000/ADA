package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.repository.GitRepository;
import com.ucl.ADA.model.repository.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BranchService {

    @Autowired
    private RepoService repoService;

    @Autowired
    private BranchRepository branchRepository;


    public Set<Branch> getBranchesByOwnerAndRepo(String user_name, String repo_name) {
        GitRepository gitRepository = repoService.findRepoByOwnerAndRepoName(user_name, repo_name);

        return branchRepository.findAllByRepository(gitRepository);
    }

    public Branch getBranchGivenOwnerRepoAndName(String user_name, String repo_name, String branch_name) {
        GitRepository gitRepository = repoService.findRepoByOwnerAndRepoName(user_name, repo_name);

        return branchRepository.findByRepositoryAndBranchName(gitRepository, branch_name);
    }
}
