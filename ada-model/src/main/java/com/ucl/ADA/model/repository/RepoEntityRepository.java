package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.repository.GitRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoEntityRepository extends CrudRepository<GitRepository, Long> {
    @Query(value = "SELECT repo_name FROM public.repository", nativeQuery = true)
    List<String> fetchRepoNames();

    GitRepository findByRepoName(String repoName);
}
