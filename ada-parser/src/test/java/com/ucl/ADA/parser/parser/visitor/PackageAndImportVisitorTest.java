package com.ucl.ADA.parser.parser.visitor;

import com.ucl.ADA.parser.parser.CompilationUnitBuilder;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackageAndImportVisitorTest {

    public static CompilationUnitBuilder compilationUnitBuilder;
    private String[] sourceDirectories = {};
    private String filePath = "WaterAnimal.java";
    private String packageName = "com.ucl.ADA.parser.test_resources";
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

    @BeforeEach
    void setUp() {
        compilationUnitBuilder = new CompilationUnitBuilder();

    }

    @Test
    public void visitTest() {
        CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(this.filePath, this.classSourceCode, this.sourceDirectories);
        PackageAndImportVisitor packageAndImportVisitor = new PackageAndImportVisitor();
        compilationUnit.accept(packageAndImportVisitor);
        assertEquals(packageAndImportVisitor.getPackageName(), this.packageName);
        assertEquals(packageAndImportVisitor.getImportedExternalClasses().size(), 0);
        assertEquals(packageAndImportVisitor.getImportedInternalClasses().size(), 0);
    }
}
