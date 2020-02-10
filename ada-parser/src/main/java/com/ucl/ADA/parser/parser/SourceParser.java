package com.ucl.ADA.parser.parser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.ucl.ADA.parser.model.*;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SourceParser {

    private final String SRC_DIRECTORY_PATH;
    private JavaSymbolSolver symbolSolver;

    public SourceParser(String SRC_DIRECTORY_PATH) {
        this.SRC_DIRECTORY_PATH = SRC_DIRECTORY_PATH;
        this.symbolSolver = getConstructedJavaSymbolSolver(this.SRC_DIRECTORY_PATH);
    }

    public Set<SourceFile> parseSource(String sourceFilePath) {

        StaticJavaParser.getConfiguration().setSymbolResolver(this.symbolSolver);

        Set<SourceFile> sourceClasses = new HashSet<SourceFile>();

        try {
            CompilationUnit cu = StaticJavaParser.parse(new File(sourceFilePath));
            Set<String> importPacakages = new HashSet<String>();
            cu.findAll(ImportDeclaration.class).forEach(p -> {
                importPacakages.add(p.toString().trim());
            });

            cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cl -> {


                // class name
                String className = cl.getNameAsString().trim();
                //  extract the parents class interfaces,
                String parentClassName = "java.lang.Object";
                if (!cl.getExtendedTypes().isEmpty()) {
                    parentClassName = cl.getExtendedTypes().get(0).toString().trim();
                }

                // implemented interfaces
                Set<String> implementedInterface = new HashSet<String>();

                cl.getImplementedTypes();
                cl.getImplementedTypes().forEach(a -> {
                    implementedInterface.add(a.toString().trim());
                });
                implementedInterface.remove(parentClassName);

                String packageName = "";
                packageName = cl.resolve().getPackageName();


                // all attributes
                List<SourceAttribute> sourceAttributes = new ArrayList<>();
                cl.findAll(FieldDeclaration.class).forEach(vv -> {
                    Set<String> modifiers = new HashSet<>();
                    vv.getModifiers().forEach(m -> {
                        modifiers.add(m.toString().trim().toLowerCase());
                    });


                    String type = vv.getCommonType().toString();
                    vv.getVariables().forEach(vk -> {
                        String name = vk.getName().toString().trim();
                        String value = "";
                        if (!vk.getInitializer().isEmpty()) {
                            value = vk.getInitializer().get().toString().trim();
                        }
                        SourceAttribute sb = new SourceAttribute(modifiers, name, type, value);
                        sourceAttributes.add(sb);
                    });
                });

                // extracted methods
                Set<SourceMethod> methods = new HashSet<SourceMethod>();
                cl.findAll(MethodDeclaration.class).forEach(n -> {
                    // name and return type
                    String methodName = n.getNameAsString();
                    String returnType = n.getType().toString();

                    // modifiers
                    Set<String> accessModifiers = new TreeSet<String>();
                    n.getModifiers().forEach(item -> accessModifiers.add(item.toString()));
                    // parameters type and name
                    Map<String, String> parameters = new HashMap<String, String>();
                    n.getParameters()
                            .forEach(param -> parameters.put(param.getName().toString(), param.getType().toString()));

                    // method call expression
                    List<MethodCall> methodCallExpression = new LinkedList<MethodCall>();
                    // used variables in method block
                    Map<String, String> usedVariables = new HashMap<String, String>();

                    n.findAll(MethodCallExpr.class).forEach(m -> {
                        try {

                            String calleeName = m.resolve().getQualifiedName();
                            Set<String> arguments = new HashSet<String>();
                            m.getArguments().forEach(a -> {
                                arguments.add(a.toString());
                            });
                            // ignore java library methods
                            if (!calleeName.substring(0, 4).equals("java")) {
                                methodCallExpression.add(new MethodCall(calleeName, arguments));
                            }

                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    });
                    n.findAll(VariableDeclarator.class).forEach(V -> {
                        usedVariables.put(V.getNameAsString(), V.getTypeAsString());
                    });
                    SourceMethod sm = new SourceMethod(methodName, returnType, accessModifiers, parameters,
                            methodCallExpression, usedVariables);
                    methods.add(sm);
                });

                List<SourceConstructor> sourceConstructor = new ArrayList<>();
                cl.findAll(ConstructorDeclaration.class).forEach(cd -> {
                    Map<String, String> constructorParameters = new HashMap<>();
                    String name = cd.getName().toString();
                    String modifiers = cd.getAccessSpecifier().toString().toUpperCase();
                    cd.getParameters().forEach(pp -> {
                        constructorParameters.put(pp.getNameAsString(), pp.getType().toString());
                    });
                    sourceConstructor.add(new SourceConstructor(name, modifiers, constructorParameters));
                });

                sourceClasses.add(new SourceFile(importPacakages, packageName, className, parentClassName, implementedInterface, methods, sourceConstructor, sourceAttributes));

            });

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return sourceClasses;
    }

    private static JavaSymbolSolver getConstructedJavaSymbolSolver(String SRC_DIRECTORY_PATH) {
        File[] directories = new File(SRC_DIRECTORY_PATH).listFiles(File::isDirectory);
        //System.out.println(Arrays.asList(SRC_DIRECTORY_PATH));
        //System.out.println(Arrays.asList(directories));
        //System.out.println("Path added to JavaParserTypeSolver");
        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        TypeSolver javaParserTypeSolver = new JavaParserTypeSolver(new File(SRC_DIRECTORY_PATH));
        combinedSolver.add(javaParserTypeSolver);
        for (File directory : directories) {
            TypeSolver jst = new JavaParserTypeSolver(new File(directory.getAbsolutePath()));
            combinedSolver.add(jst);
        }
        TypeSolver typeSolver = new ReflectionTypeSolver();
        combinedSolver.add(typeSolver);
        return new JavaSymbolSolver(combinedSolver);
    }

}
