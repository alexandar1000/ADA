package com.ucl.ADA.repository_downloader.repositories;

import com.ucl.ADA.repository_downloader.entities.GitRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoEntityRepository extends CrudRepository<GitRepository, Long> {
    @Query(value = "SELECT repo_name FROM public.repositories", nativeQuery = true)
    List<String> fetchRepoNames();

    GitRepository findByRepoName(String repoName);
}
