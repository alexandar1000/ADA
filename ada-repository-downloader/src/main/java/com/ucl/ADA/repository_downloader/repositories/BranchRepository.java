package com.ucl.ADA.repository_downloader.repositories;

import com.ucl.ADA.repository_downloader.entities.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepository extends CrudRepository<Branch, Branch.ID> {
}
