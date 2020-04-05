package com.ucl.ADA.model.dependence_information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DependenceInfoService {

    @Autowired
    private DependenceInfoRepository dependenceInfoRepository;

    public DependenceInfo saveDependenceInfo(DependenceInfo dependenceInfo) {
        return dependenceInfoRepository.save(dependenceInfo);
    }
}
