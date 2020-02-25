package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.metric_calculator.metrics.MetricServices;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.ParserServices;
import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
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

    public ProjectStructure analyseRepositoryService(String url, String branchName) {

        RepoDbPopulator populator = new RepoDbPopulator();
//        String branch = "";
//        String url = "https://github.com/sebastianvburlacu/Fitbit-JSON-Data-Generator.git";
        populator.setUrl(url);
        populator.setBranch(branchName);

        // Download repository and store metadata in DB
        // Also set the path to the downloaded directory, to be used by the parser
        try {
            repoService.downloadAndStoreRepo(populator);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        // Parse the downloaded repository.
        ProjectStructure parsedRepositoryProjectStructure;
        try {
            parsedRepositoryProjectStructure = parserServices.parseRepository(populator.getDirectoryPath());
        } catch (FileNotFoundException e) {
            parsedRepositoryProjectStructure = null;
        }

        // Calculate the metrics for the parsed repository.
        if (parsedRepositoryProjectStructure != null) {
            parsedRepositoryProjectStructure.computeAllMetrics();
        }

        return parsedRepositoryProjectStructure;
    }
}
