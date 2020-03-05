package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.owner.OwnerRepository;
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

    }
}
