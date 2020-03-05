package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerRepository;
import com.ucl.ADA.model.repository.GitRepository;
import com.ucl.ADA.model.repository.RepoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private RepoEntityRepository repoEntityRepository;

    @Autowired
    private OwnerRepository ownerRepository;


    public Set<Branch> getBranchesByOwnerAndRepo(String user_name, String repo_name) {
        Owner owner = ownerRepository.findByUserName(user_name);

        GitRepository gitRepository = repoEntityRepository.findByOwnerAndRepoName(owner, user_name);

        return branchRepository.findAllByRepository(gitRepository);
    }

    public Branch getBranchGivenOwnerRepoAndName(String user_name, String repo_name, String branch_name) {
        Owner owner = ownerRepository.findByUserName(user_name);

        GitRepository gitRepository = repoEntityRepository.findByOwnerAndRepoName(owner, user_name);

        return branchRepository.findByRepositoryAndBranchName(gitRepository, branch_name);
    }
}
