package com.ucl.ADA.model.dependence_information.invocation_information;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassedParameterRepository extends CrudRepository<PassedParameterRepository, Long> {
}
