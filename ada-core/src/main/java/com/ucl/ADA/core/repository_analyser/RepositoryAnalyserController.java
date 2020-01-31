package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.metric_calculator.metrics.MetricServices;
import com.ucl.ADA.parser.ParserServices;
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

    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public String analyseRepository() throws FileNotFoundException {

        String parsedRepository = parserServices.parseRepository();

        metricServices.computeAllMetrics();

        return "It works! :)";
    }
}


