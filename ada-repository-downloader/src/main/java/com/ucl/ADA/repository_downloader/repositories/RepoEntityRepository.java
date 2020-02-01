package com.ucl.ADA.repository_downloader.repositories;

import com.ucl.ADA.repository_downloader.entities.GitRepository;
import org.springframework.data.repository.CrudRepository;

public interface RepoEntityRepository extends CrudRepository<GitRepository, Long> {
}
