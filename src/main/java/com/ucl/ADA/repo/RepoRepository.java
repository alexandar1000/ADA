package com.ucl.ADA.repo;

import com.ucl.ADA.example.Element;
import org.springframework.data.repository.CrudRepository;

public interface RepoRepository extends CrudRepository<Element, Long> {}
