package com.ucl.ADA.parser;

import com.ucl.ADA.model.dependence_information.ProjectStructure;
import com.ucl.ADA.parser.transformer.Transformer;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ParserServices {


    /**
     * Given a downloaded repository, Calculates the dependence between classes and returns it.
     * @return ProjectDependenceTree with all the information about the parsed project/repository dependence, or a null
     * value in case of an error
     * @throws FileNotFoundException in case the repository in question was not found
     */
    public ProjectStructure parseRepository(String repositoryPath) throws FileNotFoundException {
        return new Transformer().transform(repositoryPath);
    }
}
