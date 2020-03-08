package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.repository.GitRepo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long> {

    Set<Branch> findAllByRepository(GitRepo gitRepo);

    Branch findByRepositoryAndBranchName(GitRepo gitRepo, String branch_name);

}
