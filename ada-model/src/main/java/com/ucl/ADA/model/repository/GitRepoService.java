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

        return gitRepoRepository.findAllByOwner(owner);
    }

    public GitRepo findRepoByOwnerAndRepoName(String username, String repository) {
        Owner owner = ownerService.getOwnerByName(username);

        return gitRepoRepository.findByOwnerAndRepoName(owner, repository);
    }

}
