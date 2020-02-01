package com.ucl.ADA.core.repository_analyser;

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

    /**
     * Test which is currently only a placeholder, but will be updated accordingly when the proper functionality is
     * added.
     * @throws FileNotFoundException In the case of a failed repository download, an exception will be thrown.
     */
    @Test
    void analyseRepository() throws FileNotFoundException {
        assertThat(repositoryAnalyserController.analyseRepository()).isEqualTo("It works! :)");
    }
}