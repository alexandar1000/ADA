package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.repository.GitRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface BranchRepository extends CrudRepository<Branch, Long> {

    List<Branch> findAllByOrderByBranchIDAsc();

    Set<Branch> findAllByRepository(GitRepository gitRepository);

    Branch findByRepositoryAndBranchName(GitRepository gitRepository, String branch_name);
}
