package com.ucl.ADA.parser;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.extractor.SimpleParser;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ParserServices {

    // TODO: The contents of method should be moved to the tests and used to test the parseRepository() method.
    public String parseHardcodedRepository() throws FileNotFoundException {
        String SRC_DIRECTORY_PATH="./ada-parser//src/main/resources/source_to_parse/abc";
        String SRC_FILE_PATH = SRC_DIRECTORY_PATH + "/ServiceCentre.java";

        SimpleParser sp = new SimpleParser();
        return sp.getParsedSourceInJSON(SRC_DIRECTORY_PATH, SRC_FILE_PATH);
    }

    /**
     * Given a downloaded repository, Calculates the dependence between classes and returns it.
     * @return ProjectDependenceTree with all the information about the parsed project/repository dependence, or a null
     * value in case of an error
     * @throws FileNotFoundException in case the repository in question was not found
     */
    public ProjectDependenceTree parseRepository() throws FileNotFoundException {

        // TODO: IMPLEMENT THIS METHOD WITH THIS RETURN TYPE WHICH WILL BE EXPOSED TO THE REST OF THE PROJECT
        // You can change the passed parameters such that they fit the return type of the git_downloader_module

        return new ProjectDependenceTree();
    }
}
