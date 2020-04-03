package com.ucl.ADA.parser.parser.visitor;

import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.CompilationUnitBuilder;
import com.ucl.ADA.parser.util.SourceReader;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ADAClassVisitorTest {
    public static CompilationUnitBuilder compilationUnitBuilder;
    private String[] sourceDirectories = {};
    private Map options;


    private String filePath = "WaterAnimal.java";
    private String qualifiedName = "com.ucl.ADA.parser.test_resources.WaterAnimal";
    private String parentClassName = "com.ucl.ADA.parser.test_resources.Animal";
    private String implementedInterface = "com.ucl.ADA.parser.test_resources.IAnimal";
    List<String> declaredEnum = new ArrayList<>(Arrays.asList("Color.RED", "Color.BLUE"));

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

}
