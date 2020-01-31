package com.ucl.ADA.repository_analyser;

import com.ucl.ADA.parser.extractor.SimpleParser;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class RepositoryAnalyserServices {
    public String parseRepository() throws FileNotFoundException {
        String SRC_DIRECTORY_PATH="./ada-parser//src/main/resources/source_to_parse/abc";
        String SRC_FILE_PATH = SRC_DIRECTORY_PATH + "/ServiceCentre.java";

        SimpleParser sp = new SimpleParser();
        return sp.getParsedSourceInJSON(SRC_DIRECTORY_PATH, SRC_FILE_PATH);
    }
}
