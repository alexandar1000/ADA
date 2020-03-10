package com.ucl.ADA.parser.parser;

import com.ucl.ADA.parser.parser.visitor.PackageAndImportVisitor;
import com.ucl.ADA.parser.parser.visitor.TypeDeclarationVisitor;
import com.ucl.ADA.parser.parser.visitor.VariableDeclarationVisitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CompilationUnitBuilderTest {


    public static CompilationUnitBuilder compilationUnitBuilder;
    private String[] sourceDirectories = {};
    private Map options;


    private String filePath = "WaterAnimal.java";
    private String packageName = "com.ucl.ADA.parser.test_resources";
    private String className = "WaterAnimal";
    private String enumName = "Game";
    private String implementedInterface = "IAnimal";

    private String classSourceCode = "package com.ucl.ADA.parser.test_resources;\n" +
            "\n" +
            "public class WaterAnimal implements IAnimal {\n" +
            "    void swim(int time) {\n" +
            "        int x = 5;\n" +
            "        int y = 6;\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "enum Game {}";
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


}
