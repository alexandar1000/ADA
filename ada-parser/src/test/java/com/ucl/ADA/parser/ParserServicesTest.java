package com.ucl.ADA.parser;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.ADAParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ParserServicesTest {

    private ParserServices parserServices;

    @BeforeEach
    void setUp() {
        parserServices = new ParserServices();
    }

    @Test
    void parseRepository() {
        String resourceName = "WaterAnimal.java";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String absolutePath = file.getAbsolutePath();
        String repositoryPath = "";
        Set<String> filePaths = new HashSet<>();
        filePaths.add(absolutePath);
        SetMultimap<String, ADAClass> s = new ADAParser().getParsedSourceFile(repositoryPath, filePaths);
        assertNotNull(s);
    }
}
