package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.repository.GitRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long> {

    Set<Branch> findAllByRepository(GitRepository gitRepository);

    Branch findByRepositoryAndBranchName(GitRepository gitRepository, String branch_name);

}
