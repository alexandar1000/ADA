package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.metric_calculator.metrics.MetricServices;
import com.ucl.ADA.metric_calculator.metrics_structure.ProjectMetrics;
import com.ucl.ADA.parser.ParserServices;
import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.repository_downloader.repo.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class RepositoryAnalyserServices {

    @Autowired
    ParserServices parserServices;

    @Autowired
    RepoService repoService;

    @Autowired
    MetricServices metricServices;

    /**
     * Handles the entire analysis of the repository and unifies the remaining three modules.
     * @return ProjectMetrics object containing the resulting metric values between the objects, or null if there was
     * an error
     */
    public ProjectMetrics analyseRepositoryService() {

        // TODO: Incorporate the downloader into this

        // Parse the downloaded repository.
        ProjectDependenceTree parsedRepositoryProjectDependenceTree;
        try {
            parsedRepositoryProjectDependenceTree = parserServices.parseRepository();
        } catch (FileNotFoundException e) {
            parsedRepositoryProjectDependenceTree = null;
        }

        // Calculate the metrics for the parsed repository.
        ProjectMetrics parsedRepositoryMetrics = metricServices.computeAllMetrics(parsedRepositoryProjectDependenceTree);

        return parsedRepositoryMetrics;
    }
}
