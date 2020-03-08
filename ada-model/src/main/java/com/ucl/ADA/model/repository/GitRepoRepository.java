package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.owner.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GitRepoRepository extends CrudRepository<GitRepo, Long> {

    @Query(value = "SELECT repo_name FROM public.repository ORDER BY id ASC", nativeQuery = true)
    List<String> fetchRepoNames();

    List<GitRepo> findAllByOrderByIdAsc();

    Set<GitRepo> findAllByOwner(Owner owner);

    GitRepo findByOwnerAndRepoName(Owner owner, String repoName);
}
