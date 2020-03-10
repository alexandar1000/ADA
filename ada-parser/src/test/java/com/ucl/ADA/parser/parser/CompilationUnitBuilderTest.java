package com.ucl.ADA.parser.parser;

import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.visitor.ADAClassVisitor;
import com.ucl.ADA.parser.parser.visitor.PackageAndImportVisitor;
import com.ucl.ADA.parser.parser.visitor.TypeDeclarationVisitor;
import com.ucl.ADA.parser.parser.visitor.VariableDeclarationVisitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
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

    private String classSourceCode = "package com.ucl.ADA.parser.test_resources;\n" +
            "\n" +
            "public class WaterAnimal extends Animal implements IAnimal {\n" +
            "\n" +
            "    private int id;\n" +
            "    private String name;\n" +
            "\n" +
            "\n" +
            "    enum Color {RED, BLUE;}\n" +
            "\n" +
            "    void swim(int time) {\n" +
            "        int x = 5;\n" +
            "        int y = 6;\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "class Animal {\n" +
            "\n" +
            "}\n" +
            "\n" +
            "interface IAnimal {\n" +
            "}\n" +
            "\n" +
            "enum Game {}\n";
    private String interfaceSourceCode = "";
    private String enumSourceCode = "";


    @BeforeEach
    void setUp() {
        compilationUnitBuilder = new CompilationUnitBuilder();

        options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);

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
    public void packageAndImportVisitorTest() {
        CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories);
        PackageAndImportVisitor packageAndImportVisitor = new PackageAndImportVisitor();
        compilationUnit.accept(packageAndImportVisitor);
        assertEquals(packageAndImportVisitor.getPackageName(), this.packageName);
        assertEquals(packageAndImportVisitor.getImportedExternalClasses().size(), 0);
        assertEquals(packageAndImportVisitor.getImportedInternalClasses().size(), 0);
    }

    @Test
    public void typeDeclarationVisitorTest() {
        CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories);
        TypeDeclarationVisitor typeVisitor = new TypeDeclarationVisitor();
        compilationUnit.accept(typeVisitor);
        List<AbstractTypeDeclaration> allClassAndEnums = typeVisitor.getAbstractTypeDeclaration();
        assertEquals(allClassAndEnums.size(), 2);
        assertEquals(allClassAndEnums.get(0).getName().toString(), this.className);
        assertEquals(allClassAndEnums.get(1).getName().toString(), this.enumName);

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
            ADAClassVisitor ADAClassVisitor = new ADAClassVisitor(packageVisitor.getPackageName(), packageVisitor.getImportedInternalClasses(), packageVisitor.getImportedExternalClasses());
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


}
