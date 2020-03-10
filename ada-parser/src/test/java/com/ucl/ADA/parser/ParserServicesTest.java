package com.ucl.ADA.parser;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserServicesTest {

    private ParserServices parserServices;

    @BeforeEach
    void setUp() {
        parserServices = new ParserServices();
    }

    @Test
    void parseRepository() throws FileNotFoundException {
        String src_dir = System.getProperty("user.dir")+"/src/main/resources/source_to_parse";
        assertEquals(parserServices.parseRepository(src_dir).getClass(), ProjectStructure.class);
    }
}
