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


    public Set<Branch> getBranchesByOwnerAndRepo(String username, String repository) {
        GitRepo gitRepo = gitRepoService.findRepoByOwnerAndRepoName(username, repository);

        return branchRepository.findAllByRepository(gitRepo);
    }

    public Branch getBranchGivenOwnerRepoAndName(String username, String repository, String branch) {
        GitRepo gitRepo = gitRepoService.findRepoByOwnerAndRepoName(username, repository);

        return branchRepository.findByRepositoryAndBranchName(gitRepo, branch);
    }
}
