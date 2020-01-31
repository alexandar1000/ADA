package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.core.repository_analyser.RepositoryAnalyserController;
import com.ucl.ADA.core.repository_analyser.RepositoryAnalyserServices;
import com.ucl.ADA.metric_calculator.metrics.MetricServices;
import com.ucl.ADA.parser.ParserServices;
import com.ucl.ADA.repository_downloader.repo.RepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

class RepositoryAnalyserControllerTest {

    @InjectMocks
    private RepositoryAnalyserController repositoryAnalyserController;

    @Mock
    RepositoryAnalyserServices repositoryAnalyserServices;

    @Mock
    ParserServices parserServices;

    @Mock
    RepoService repoService;

    @Mock
    MetricServices metricServices;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void analyseRepository() throws FileNotFoundException {
        assertThat(repositoryAnalyserController.analyseRepository()).isEqualTo("It works! :)");
    }
}