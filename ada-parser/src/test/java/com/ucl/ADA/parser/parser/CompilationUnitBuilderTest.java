package com.ucl.ADA.parser.parser;

;
import com.ucl.ADA.parser.util.SourceReader;
import org.eclipse.jdt.core.JavaCore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompilationUnitBuilderTest {


    public static CompilationUnitBuilder compilationUnitBuilder;
    private String[] sourceDirectories = {};
    private Map options;


    private String filePath = "WaterAnimal.java";

    private String classSourceCode = "";


    @BeforeEach
    void setUp() {
        compilationUnitBuilder = new CompilationUnitBuilder();
        options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
        String resourceName = "WaterAnimal.java";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String absolutePath = file.getAbsolutePath();
        classSourceCode = SourceReader.getSourceCodeFromSourcePath(absolutePath);
    }

    @Test
    public void buildASTParserTest() {
        assertNotEquals(compilationUnitBuilder.buildASTParser(this.sourceDirectories, this.options), null);
    }

    @Test
    public void getParserVersion_1_5Test() {
        assertEquals(compilationUnitBuilder.getParserVersion_1_5(), options);
    }

    @Test
    public void getCompilationUnitTest() {
        assertNotEquals(compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories), null);

    }


}
