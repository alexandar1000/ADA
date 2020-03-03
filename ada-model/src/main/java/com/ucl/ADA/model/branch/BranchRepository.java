package com.ucl.ADA.model.branch;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, Long> {

    List<Branch> findAllByOrderByBranchIDAsc();

}
