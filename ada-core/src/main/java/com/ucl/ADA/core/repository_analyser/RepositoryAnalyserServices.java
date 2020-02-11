package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.metric_calculator.metrics.MetricServices;
import com.ucl.ADA.metric_calculator.metrics_structure.ProjectMetricsContainer;
import com.ucl.ADA.parser.ParserServices;
import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
import com.ucl.ADA.repository_downloader.services.RepoService;
import org.eclipse.jgit.api.errors.GitAPIException;
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
    public ProjectMetricsContainer analyseRepositoryService(String branchName, String url) {

        RepoDbPopulator populator = new RepoDbPopulator();
//        String branch = "";
//        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";
        populator.setUrl(url);
        populator.setBranch(branchName);

        try {
            populator = RepoDownloader.downloadRepository(populator);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        // Parse the downloaded repository.
        ProjectDependenceTree parsedRepositoryProjectDependenceTree;
        try {
            parsedRepositoryProjectDependenceTree = parserServices.parseRepository(populator.getDirectoryPath());
        } catch (FileNotFoundException e) {
            parsedRepositoryProjectDependenceTree = null;
        }

        // Calculate the metrics for the parsed repository.
        ProjectMetricsContainer parsedRepositoryMetrics = metricServices.computeAllMetrics(parsedRepositoryProjectDependenceTree);

        return parsedRepositoryMetrics;
    }
}
