package com.ucl.ADA.parser;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.ADAParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserServicesTest {

    private ParserServices parserServices;

    @BeforeEach
    void setUp() {
        parserServices = new ParserServices();
    }

    @Test
    void parseRepository() {
        String repositoryPath = "temp/alexandar1000/ADA/master/2020-03-06-18-00-20";
        List<String> filePaths = new ArrayList<>();
        filePaths.add("ada-parser/src/main/java/com/ucl/ADA/parser/parser/ADAParser.java");
        SetMultimap<String, ADAClass> s = new ADAParser().getParsedSourceFile(repositoryPath, filePaths);
        assertNotNull(s);
    }
}
