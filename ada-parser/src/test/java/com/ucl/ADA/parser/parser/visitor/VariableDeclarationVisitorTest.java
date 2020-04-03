package com.ucl.ADA.parser.parser.visitor;

import com.ucl.ADA.parser.parser.CompilationUnitBuilder;
import com.ucl.ADA.parser.util.SourceReader;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariableDeclarationVisitorTest {

    public static CompilationUnitBuilder compilationUnitBuilder;
    private String[] sourceDirectories = {};
    private String filePath = "WaterAnimal.java";

    private String classSourceCode = "";

    @BeforeEach
    void setUp() {
        compilationUnitBuilder = new CompilationUnitBuilder();
        String resourceName = "WaterAnimal.java";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String absolutePath = file.getAbsolutePath();
        classSourceCode = SourceReader.getSourceCodeFromSourcePath(absolutePath);
    }

    @Test
    public void variableDeclarationVisitorVisitorTest() {
        CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories);
        VariableDeclarationVisitor variableDeclarationVisitor = new VariableDeclarationVisitor();
        compilationUnit.accept(variableDeclarationVisitor);
        Map<String, String> localVariables = new HashMap<String, String>() {{
            put("ani_x", "com.ucl.ADA.parser.test_resources.Animal");
            put("ani_y", "com.ucl.ADA.parser.test_resources.Animal");
        }};
        assertEquals(variableDeclarationVisitor.getLocalVariables(), localVariables);
    }


}
