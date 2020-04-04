package com.ucl.ADA.model.static_information;

import com.ucl.ADA.model.class_structure.ClassStructure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaticInfoRepository extends CrudRepository<StaticInfo, Long> {

    StaticInfo findByClassStructures(ClassStructure classStructure);

}
