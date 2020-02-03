package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.metric_calculator.metrics.MetricServices;
import com.ucl.ADA.parser.ParserServices;
import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.repository_downloader.repo.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/analyser")
public class RepositoryAnalyserController {

    @Autowired
    RepositoryAnalyserServices repositoryAnalyserServices;

    @Autowired
    ParserServices parserServices;

    @Autowired
    RepoService repoService;

    @Autowired
    MetricServices metricServices;

    /**
     * A top level endpoint which will analyse the entire repository branch which is provided, at the current time
     * point. Currently awaiting other modules to be developed.
     * @return Currently only a string, but in the future something which links to the resulting data.
     * @throws FileNotFoundException In the case of a failed repository download, an exception will be thrown.
     */
    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public String analyseRepository() throws FileNotFoundException {

        String parsedRepository = parserServices.parseRepository();

        metricServices.computeAllMetrics();

        ProjectDependenceTree projectDependenceTree = new ProjectDependenceTree().randomParsedDataOfRepoPlaceholder();

        return projectDependenceTree.toString();
    }
}


