package com.ucl.ADA.parser;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.ADAParser;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class ParserServices {


    public ParserServices() {
    }

    /**
     * Parse the given code repository and generates a map of ADAClass
     *
     * @param repositoryPath root path of the source code repository
     * @param filePaths      set of file path(relative) that needs to be parsed
     * @return a map of parsed files in from of path->ADAClass
     */
    public SetMultimap<String, ADAClass> parseRepository(String repositoryPath, Set<String> filePaths) {
        return new ADAParser().getParsedSourceFile(repositoryPath, filePaths);
    }
}
