package com.ucl.ADA.parser;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.transformer.Transformer;
import com.ucl.ADA.parser.parser.ADAParser;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ParserServices {

    // TODO: move testing method to test dir
    public void parseHardcodedRepository() throws FileNotFoundException {
        String SRC_DIRECTORY_PATH="ada-parser/src/main/resources/source_to_parse";
        String SRC_FILE_PATH = SRC_DIRECTORY_PATH + "/ServiceCentre.java";

        ADAParser parser = new ADAParser();
        parser.printParsedSourceFileInJSON(SRC_DIRECTORY_PATH);
    }

    /**
     * Given a downloaded repository, Calculates the dependence between classes and returns it.
     * @return ProjectDependenceTree with all the information about the parsed project/repository dependence, or a null
     * value in case of an error
     * @throws FileNotFoundException in case the repository in question was not found
     */
    // TODO: the parameters should fit the return type of the git downloader
    public ProjectDependenceTree parseRepository(String src_dir) throws FileNotFoundException {

//        return new ProjectDependenceTree();
        return new Transformer().transform(src_dir);
    }
}
