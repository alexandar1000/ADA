package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GitRepoService {

    @Autowired
    private GitRepoRepository gitRepoRepository;

    @Autowired
    private OwnerService ownerService;


    public List<GitRepo> listRepositories() {
        return gitRepoRepository.findAllByOrderByIdAsc();
    }

    public List<String> listRepoNames() {
        return gitRepoRepository.fetchRepoNames();
    }

    public Set<GitRepo> findAllReposByOwner(String user_name) {
        Owner owner = ownerService.getOwnerByName(user_name);

        Set<GitRepo> gitRepositories = gitRepoRepository.findAllByOwner(owner);

        return gitRepositories;
    }

    public GitRepo findRepoByOwnerAndRepoName(String user_name, String repo_name) {
        Owner owner = ownerService.getOwnerByName(user_name);

        GitRepo gitRepository = gitRepoRepository.findByOwnerAndRepoName(owner, repo_name);

        return gitRepository;
    }

}
