package com.ucl.ADA.model.static_information.static_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaticInfoService {

    @Autowired
    private StaticInfoRepository staticInfoRepository;

    public StaticInfo saveStaticInfo(StaticInfo staticInfo) {
        return staticInfoRepository.save(staticInfo);
    }
}
