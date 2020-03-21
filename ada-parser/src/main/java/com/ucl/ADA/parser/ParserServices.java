package com.ucl.ADA.parser;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.ADAParser;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParserServices {


    /**
     * Parse the given code repository and generates a map of ADAClass
     *
     * @param repositoryPath root path of the source code repository
     * @param filePaths      list of file path(relative) that needs to be parsed
     * @return a map of parsed files in from of path->ADAClass
     */
    public SetMultimap<String, ADAClass> parseRepository(String repositoryPath, List<String> filePaths) {
        return new ADAParser().getParsedSourceFile(repositoryPath, filePaths);
    }

}
