package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RepoService {

    @Autowired
    private RepoEntityRepository repoEntityRepository;

    @Autowired
    private OwnerService ownerService;


    public List<GitRepository> listRepositories() {
        return repoEntityRepository.findAllByOrderByRepoIDAsc();
    }

    public List<String> listRepoNames() {
        return repoEntityRepository.fetchRepoNames();
    }

    public Set<GitRepository> findAllReposByOwner(String user_name) {
        Owner owner = ownerService.getOwnerByName(user_name);

        return repoEntityRepository.findAllByOwner(owner);
    }

    public GitRepository findRepoByOwnerAndRepoName(String user_name, String repo_name) {
        Owner owner = ownerService.getOwnerByName(user_name);

        return repoEntityRepository.findByOwnerAndRepoName(owner, repo_name);
    }

}
