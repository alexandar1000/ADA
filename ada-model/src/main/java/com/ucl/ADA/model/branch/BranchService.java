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


    /**
     * Get all branches given a Git repository name and the username of its owner
     * @param username of the owner of the repository
     * @param repository name of the repository
     * @return set of all branches corresponding to the owner and repository
     */
    public Set<Branch> getBranchesByOwnerAndRepo(String username, String repository) {
        GitRepo gitRepo = gitRepoService.findRepoByOwnerAndRepoName(username, repository);

        return branchRepository.findAllByRepository(gitRepo);
    }

    /**
     * Get a specific branch given a Git repository name, username of its owner and branch name
     * @param username of the owner in the repository
     * @param repository name of the repository
     * @param branch name of the branch
     * @return the corresponding branch entity
     */
    public Branch getBranchGivenOwnerRepoAndName(String username, String repository, String branch) {
        GitRepo gitRepo = gitRepoService.findRepoByOwnerAndRepoName(username, repository);

        return branchRepository.findByRepositoryAndBranchName(gitRepo, branch);
    }

    /**
     * Add a Branch entity to the database
     * @param branch Branch entity to be added
     * @return the newly added Branch entity
     */
    public Branch addBranch(Branch branch){
        return branchRepository.save(branch);
    }

    public Branch getBranchGivenRepoAndName(GitRepo gitRepo, String branch){
        return branchRepository.findByRepositoryAndBranchName(gitRepo, branch);
    }
}