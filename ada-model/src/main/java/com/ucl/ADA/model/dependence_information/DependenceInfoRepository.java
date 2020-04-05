package com.ucl.ADA.model.dependence_information;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependenceInfoRepository extends CrudRepository<DependenceInfo, Long> {

}
