package com.ucl.ADA.parser.parser;

import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.visitor.ADAClassVisitor;
import com.ucl.ADA.parser.parser.visitor.PackageAndImportVisitor;
import com.ucl.ADA.parser.parser.visitor.TypeDeclarationVisitor;
import com.ucl.ADA.parser.parser.visitor.VariableDeclarationVisitor;
import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompilationUnitBuilderTest {


    public static CompilationUnitBuilder compilationUnitBuilder;
    private String[] sourceDirectories = {};
    private Map options;


    private String filePath = "WaterAnimal.java";
    private String packageName = "com.ucl.ADA.parser.test_resources";
    private String className = "WaterAnimal";
    private String qualifiedName = "com.ucl.ADA.parser.test_resources.WaterAnimal";
    private String parentClassName = "com.ucl.ADA.parser.test_resources.Animal";
    private String enumName = "Game";
    private String implementedInterface = "com.ucl.ADA.parser.test_resources.IAnimal";
    List<String> declaredEnum = new ArrayList<>(Arrays.asList("Color.RED", "Color.BLUE"));

    private String classSourceCode = "";
    private String interfaceSourceCode = "";
    private String enumSourceCode = "";


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
        classSourceCode = getSourceCodeFromSourcePath(absolutePath);
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


    @Test
    public void typeDeclarationVisitorTest() {
        CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories);
        TypeDeclarationVisitor typeVisitor = new TypeDeclarationVisitor();
        compilationUnit.accept(typeVisitor);
        List<AbstractTypeDeclaration> allClassAndEnums = typeVisitor.getAbstractTypeDeclaration();
        assertEquals(allClassAndEnums.get(0).getName().toString(), this.className);
        assertEquals(allClassAndEnums.get(3).getName().toString(), this.enumName);

    }


    @Test
    public void variableDeclarationVisitorVisitorTest() {
        CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories);
        VariableDeclarationVisitor variableDeclarationVisitor = new VariableDeclarationVisitor();
        compilationUnit.accept(variableDeclarationVisitor);
        Map<String, String> localVariables = new HashMap<String, String>() {{
            put("x", "int");
            put("y", "int");
        }};
        assertEquals(variableDeclarationVisitor.getLocalVariables(), localVariables);
    }

    @Test
    public void aDAClassVisitorVisitorTest() {
        CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories);
        List<ADAClass> parsedClasses = new ArrayList<>();
        PackageAndImportVisitor packageVisitor = new PackageAndImportVisitor();
        compilationUnit.accept(packageVisitor);
        TypeDeclarationVisitor typeVisitor = new TypeDeclarationVisitor();
        compilationUnit.accept(typeVisitor);
        List<AbstractTypeDeclaration> allClassAndEnums = typeVisitor.getAbstractTypeDeclaration();
        assertEquals(allClassAndEnums.size(), 4);
        for (int i = 0; i < allClassAndEnums.size(); i++) {
            AbstractTypeDeclaration classAndEnumType = allClassAndEnums.get(i);
            ADAClassVisitor ADAClassVisitor = new ADAClassVisitor(packageVisitor.getPackageName(), packageVisitor.getImportedPackagesAndClasses());
            classAndEnumType.accept(ADAClassVisitor);
            ADAClass extractedClass = ADAClassVisitor.getExtractedClass();
            parsedClasses.add(extractedClass);
        }

        ADAClass adaClassWater = parsedClasses.get(0);
        assertEquals(adaClassWater.getClassName(), this.qualifiedName);
        assertEquals(adaClassWater.getParentClassName(), this.parentClassName);
        assertFalse(adaClassWater.isInterface());
        assertFalse(adaClassWater.isEnum());
        assertTrue(adaClassWater.getImplementedInterfaces().contains(this.implementedInterface));
        assertEquals(adaClassWater.getDeclaredEnums(), declaredEnum);

    }


    /**
     * This method reads the contents from a given source file path.
     * If the file is empty or cannot be found in the given file path it return a empty string
     *
     * @param sourceFilePath Source file path that content has to be read from the path.
     * @return A single string containing the source code written in that file.
     * And containing empty string if the file is empty or cannot be found in the specified location.
     * @IOException if errors occur in IO operation
     */
    public static String getSourceCodeFromSourcePath(String sourceFilePath) {
        String sourceCode = "";

        File sourceFile = new File(sourceFilePath);
        if (sourceFile.exists()) {
            try {
                sourceCode = FileUtils.readFileToString(sourceFile, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sourceCode;
    }


}
