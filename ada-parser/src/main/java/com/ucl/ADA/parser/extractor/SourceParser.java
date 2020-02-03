package com.ucl.ADA.parser.extractor;


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
    private final String SRC_FILE_PATH;
    private TypeSolver typeSolver;
    private TypeSolver javaParserTypeSolver;
    private CombinedTypeSolver combinedSolver;
    private JavaSymbolSolver symbolSolver;

    public SourceParser(String SRC_DIRECTORY_PATH, String SRC_FILE_PATH) {
        this.SRC_DIRECTORY_PATH = SRC_DIRECTORY_PATH;
        this.SRC_FILE_PATH = SRC_FILE_PATH;

        this.typeSolver = new ReflectionTypeSolver();
        this.javaParserTypeSolver = new JavaParserTypeSolver(new File(this.SRC_DIRECTORY_PATH));
        this.combinedSolver = new CombinedTypeSolver();
        this.combinedSolver.add(this.javaParserTypeSolver);
        this.combinedSolver.add(this.typeSolver);
        this.symbolSolver = new JavaSymbolSolver(this.combinedSolver);
    }

    public Set<SourceFile> parseSource() {

        StaticJavaParser.getConfiguration().setSymbolResolver(this.symbolSolver);

        Set<SourceFile> sourceClasses = new HashSet<SourceFile>();

        try {
            CompilationUnit cu = StaticJavaParser.parse(new File(this.SRC_FILE_PATH));

            cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cl -> {
                // class name
                String className = cl.getNameAsString();
                // all static variables
                Map<String, String> staticVariables = new HashMap<String, String>();
                cl.resolve().getAllStaticFields().forEach(x -> {
                    staticVariables.put(x.getName(), x.getType().describe());
                });
                //  extract the parents class interfaces,
                String parentClassName ="";
                if (!cl.getExtendedTypes().isEmpty())
                    parentClassName = cl.getExtendedTypes().get(0).toString();

                // implemented interfaces
                Set<String> implementedInterface = new HashSet<String>();
                cl.resolve().getAncestors().forEach(a -> {
                    implementedInterface.add(a.getQualifiedName());
                });
                implementedInterface.remove(parentClassName);

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
                        String callee = ((MethodCallExpr) m).resolve().getQualifiedName().toString();
                        methodCallExpression.add(callee);
                    });

                    n.findAll(VariableDeclarator.class).forEach(V -> {
                        VariableDeclarator v = (VariableDeclarator) V;
                        usedVariables.put(v.getNameAsString(), v.getTypeAsString());
                    });

                    SourceMethod sm = new SourceMethod(methodName, returnType, accessModifiers, parameters,
                            methodCallExpression, usedVariables);
                    methods.add(sm);
                });
                sourceClasses.add(new SourceFile(className, parentClassName, implementedInterface, staticVariables, methods));

            });

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return sourceClasses;
    }

}
