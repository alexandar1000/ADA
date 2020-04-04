package com.ucl.ADA.model.dependence_information;

import com.ucl.ADA.model.class_structure.ClassStructure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependenceInfoRepository extends CrudRepository<DependenceInfo, Long> {

    DependenceInfo findByClassStructures(ClassStructure classStructure);

}
