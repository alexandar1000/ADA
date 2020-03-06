package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.owner.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RepoEntityRepository extends CrudRepository<GitRepository, Long> {

    @Query(value = "SELECT repo_name FROM public.repository ORDER BY repo_id ASC", nativeQuery = true)
    List<String> fetchRepoNames();

    List<GitRepository> findAllByOrderByRepoIDAsc();

    Set<GitRepository> findAllByOwner(Owner owner);

    GitRepository findByOwnerAndRepoName(Owner owner, String repoName);
}
