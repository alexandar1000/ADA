package com.ucl.ADA.parser;

import com.ucl.ADA.parser.dependence_information.ProjectStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class ParserServicesTest {

    private ParserServices parserServices;

    @BeforeEach
    void setUp() {
        parserServices = new ParserServices();
    }

    @Test
    void parseRepository() throws FileNotFoundException {
        String src_dir = "ada-parser/src/main/resources/source_to_parse";
        assert parserServices.parseRepository(src_dir).getClass() == ProjectStructure.class;
    }
}
