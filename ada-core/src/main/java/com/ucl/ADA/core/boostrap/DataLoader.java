package com.ucl.ADA.core.boostrap;

import com.ucl.ADA.core.repository_analyser.RepositoryAnalyserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RepositoryAnalyserServices repositoryAnalyserServices;

    @Override
    public void run(String... args) throws Exception {
        // TODO: This is a 'integration testing call and should be omitted for ow'
        loadData();
    }

    private void loadData() throws FileNotFoundException {
        repositoryAnalyserServices.analyseRepositoryService("https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0", "master");
    }
}
