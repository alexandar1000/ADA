package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.metric_calculator.metrics_structure.ProjectMetricsContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyser")
public class RepositoryAnalyserController {
    @Autowired
    RepositoryAnalyserServices repositoryAnalyserServices;

    /**
     * A top level endpoint which will analyse the entire repository branch which is provided, at the current time
     * point. Currently awaiting other modules to be developed.
     * @return Currently only a string, but in the future something which links to the resulting data.
     */
    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public ProjectMetricsContainer analyseRepository() {
        return repositoryAnalyserServices.analyseRepositoryService();
    }
}


