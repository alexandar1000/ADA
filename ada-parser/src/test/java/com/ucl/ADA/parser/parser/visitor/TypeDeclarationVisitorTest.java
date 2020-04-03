package com.ucl.ADA.parser.parser.visitor;

import com.ucl.ADA.parser.parser.CompilationUnitBuilder;
import com.ucl.ADA.parser.util.SourceReader;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeDeclarationVisitorTest {

    public static CompilationUnitBuilder compilationUnitBuilder;
    private String[] sourceDirectories = {};
    private String className = "WaterAnimal";
    private String enumName = "Game";

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
    public void visitTest() {
        CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories);
        TypeDeclarationVisitor typeVisitor = new TypeDeclarationVisitor();
        compilationUnit.accept(typeVisitor);
        List<AbstractTypeDeclaration> allClassAndEnums = typeVisitor.getAbstractTypeDeclaration();
        assertEquals(allClassAndEnums.get(0).getName().toString(), this.className);
        assertEquals(allClassAndEnums.get(3).getName().toString(), this.enumName);

    }

}
