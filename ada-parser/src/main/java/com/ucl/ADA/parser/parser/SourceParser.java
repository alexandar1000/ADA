package com.ucl.ADA.parser.parser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.ucl.ADA.parser.model.SourceFile;
import com.ucl.ADA.parser.model.SourceMethod;

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
            cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cl -> {
                // class name
                String className = cl.getNameAsString();
                //  extract the parents class interfaces,
                String parentClassName = "java.lang.Object";
                if (!cl.getExtendedTypes().isEmpty()) {
                    parentClassName = cl.getExtendedTypes().get(0).toString();
                }

                // implemented interfaces
                Set<String> implementedInterface = new HashSet<String>();

                cl.getImplementedTypes();
                cl.getImplementedTypes().forEach(a -> {
                    implementedInterface.add(a.toString());
                });
                implementedInterface.remove(parentClassName);

                String packageName = "";
                packageName = cl.resolve().getPackageName();

                // all static fields
                Map<String, String> staticFields = new HashMap<String, String>();
                cl.resolve().getAllStaticFields().forEach(x -> {
                    staticFields.put(x.getName(), x.getType().describe());
                });

                // all public fields
                Map<String, String> publicFields = new HashMap<String, String>();
                cl.resolve().getAllFields().forEach(f -> {
                    if (f.accessSpecifier().name().equals("PUBLIC")) {
                        publicFields.put(f.getName(), f.getType().describe());
                    }
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
                    List<String> methodCallExpression = new LinkedList<String>();
                    // used variables in method block
                    Map<String, String> usedVariables = new HashMap<String, String>();

                    n.findAll(MethodCallExpr.class).forEach(m -> {
                        try {
                            String callee = m.resolve().getQualifiedName();

                            // ignore java library methods
                            if (!callee.substring(0, 4).equals("java"))
                                methodCallExpression.add(callee);
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
                sourceClasses.add(new SourceFile(packageName, className, parentClassName, implementedInterface, staticFields, publicFields, methods));

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
        System.out.println("Path added to JavaParserTypeSolver");
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
