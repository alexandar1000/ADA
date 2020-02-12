package com.ucl.ADA.parser.parser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.ucl.ADA.parser.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SourceParser {

    private final String SRC_DIRECTORY_PATH;
    private final JavaSymbolSolver SYMBOL_SOLVER;

    public SourceParser(String SRC_DIRECTORY_PATH) {
        this.SRC_DIRECTORY_PATH = SRC_DIRECTORY_PATH;
        this.SYMBOL_SOLVER = getConstructedJavaSymbolSolver(this.SRC_DIRECTORY_PATH);
    }

    public Set<SourceFile> getParsedSourceClassesForGivenSourceFile(String sourceFilePath) {
        StaticJavaParser.getConfiguration().setSymbolResolver(this.SYMBOL_SOLVER);
        Set<SourceFile> sourceClasses = new HashSet<>();
        try {
            CompilationUnit cu = StaticJavaParser.parse(new File(sourceFilePath));
            sourceClasses.addAll(getAllParsedSourceClasses(cu));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return sourceClasses;
    }

    private Set<SourceFile> getAllParsedSourceClasses(CompilationUnit cu) {
        Set<SourceFile> sourceClasses = new HashSet<>();
        Set<String> importedPackages = getImportedPackages(cu);
        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cl -> {
            SourceFile sf = getParsedSourceClass(cl, importedPackages);
            sourceClasses.add(sf);
        });
        return sourceClasses;
    }

    private SourceFile getParsedSourceClass(ClassOrInterfaceDeclaration cl, Set<String> importedPackages) {
        String packageName = getPackageName(cl);
        String className = getClassName(cl);
        String parentClassName = getParentClassName(cl);
        Set<String> implementedInterfaces = getImplementedInterfaces(cl, parentClassName);
        List<SourceAttribute> sourceAttributes = getAllSourceAttributes(cl);
        List<SourceConstructor> constructorDeclarations = getAllConstructorDeclaration(cl);
        Set<SourceMethod> declaredMethods = getAllDeclaredMethods(cl);
        SourceFile sf = new SourceFile(importedPackages, packageName, className, parentClassName, implementedInterfaces, declaredMethods, constructorDeclarations, sourceAttributes);
        return sf;
    }

    private Set<String> getImportedPackages(CompilationUnit cu) {
        Set<String> importedPackages = new HashSet<>();
        cu.findAll(ImportDeclaration.class).forEach(p -> {
            try {
                importedPackages.add(p.getName().toString().trim());
            } catch (UnsolvedSymbolException un) {
                System.err.println("Occurred in  ImportDeclaration:->" + un.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return importedPackages;
    }

    private String getPackageName(ClassOrInterfaceDeclaration cl) {
        String packageName = "";
        try {
            packageName = cl.resolve().getPackageName();
        } catch (UnsolvedSymbolException un) {
            System.err.println("Occurred in  Package Name extraction:->" + un.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return packageName;
    }

    private String getClassName(ClassOrInterfaceDeclaration cl) {
        return cl.getNameAsString().trim();
    }

    private String getParentClassName(ClassOrInterfaceDeclaration cl) {
        String parentClassName = "java.lang.Object";
        try {
            if (!cl.getExtendedTypes().isEmpty()) {
                parentClassName = cl.getExtendedTypes().get(0).toString().trim();
            }
        } catch (UnsolvedSymbolException un) {
            System.err.println("Occurred in  Parent Class Name extraction:->" + un.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return parentClassName;
    }

    private Set<String> getImplementedInterfaces(ClassOrInterfaceDeclaration cl, String parentClassName) {
        Set<String> implementedInterface = new HashSet<>();
        try {
            cl.getImplementedTypes();
            cl.getImplementedTypes().forEach(a -> {
                implementedInterface.add(a.toString().trim());
            });
            implementedInterface.remove(parentClassName);
        } catch (UnsolvedSymbolException un) {
            System.err.println("Occurred in  Implemented Interfaces Name extraction:->" + un.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return implementedInterface;
    }

    private List<SourceAttribute> getAllSourceAttributes(ClassOrInterfaceDeclaration cl) {
        List<SourceAttribute> sourceAttributes = new ArrayList<>();
        cl.findAll(FieldDeclaration.class).forEach(vv -> {
            try {
                Set<String> modifiers = new HashSet<>();
                vv.getModifiers().forEach(m -> {
                    modifiers.add(m.toString().toLowerCase().trim());
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
            } catch (UnsolvedSymbolException un) {
                System.err.println("Occurred in FieldDeclaration:-> " + un.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return sourceAttributes;
    }

    private List<SourceConstructor> getAllConstructorDeclaration(ClassOrInterfaceDeclaration cl) {
        List<SourceConstructor> sourceConstructors = new ArrayList<>();
        cl.findAll(ConstructorDeclaration.class).forEach(cd -> {
            try {
                Map<String, String> constructorParameters = new HashMap<>();
                String name = cd.getName().toString();
                String modifiers = cd.getAccessSpecifier().toString().toUpperCase();
                cd.getParameters().forEach(pp -> {
                    constructorParameters.put(pp.getNameAsString(), pp.getType().toString());
                });
                sourceConstructors.add(new SourceConstructor(name, modifiers, constructorParameters));
            } catch (UnsolvedSymbolException un) {
                System.err.println("Occurred in ConstructorDeclaration:-> " + un.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return sourceConstructors;
    }

    private Set<SourceMethod> getAllDeclaredMethods(ClassOrInterfaceDeclaration cl) {
        Set<SourceMethod> sourceMethodSet = new HashSet<SourceMethod>();
        cl.findAll(MethodDeclaration.class).forEach(md -> {
            SourceMethod sm = getDeclaredMethod(md);
            sourceMethodSet.add(sm);
        });
        return sourceMethodSet;
    }

    private SourceMethod getDeclaredMethod(MethodDeclaration md) {
        String methodName = getDeclaredMethodName(md);
        String returnType = getDeclaredMethodReturnType(md);
        Set<String> accessModifiers = getAccessModifiersOfDeclaredMethod(md);
        Map<String, String> parameters = getParametersOfDeclaredMethod(md);
        Map<String, String> localVariables = getAllLocalVariablesInsideMethodDeclaration(md);
        List<MethodCall> methodCalls = getAllMethodCalls(md);
        List<ConstructorInvocation> constructorInvocations = getAllConstructorInvocations(md);
        SourceMethod sm = new SourceMethod(methodName, returnType, accessModifiers, parameters, methodCalls, localVariables, constructorInvocations);
        return sm;
    }

    private String getDeclaredMethodName(MethodDeclaration md) {
        String methodName = md.getNameAsString().trim();
        return methodName;

    }

    private String getDeclaredMethodReturnType(MethodDeclaration md) {
        String returnType = md.getType().toString().trim();
        return returnType;
    }

    private Set<String> getAccessModifiersOfDeclaredMethod(MethodDeclaration md) {
        Set<String> accessModifiers = new TreeSet<String>();
        md.getModifiers().forEach(item -> {
            try {
                accessModifiers.add(item.toString().trim());
            } catch (UnsolvedSymbolException un) {
                System.err.println("Occurred in MethodDeclaration to getModifiers():->" + un.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return accessModifiers;
    }

    private Map<String, String> getParametersOfDeclaredMethod(MethodDeclaration md) {
        Map<String, String> parameters = new HashMap<String, String>();
        md.getParameters().forEach(param -> {
            try {
                parameters.put(param.getName().toString(), param.getType().toString());
            } catch (UnsolvedSymbolException un) {
                System.err.println("Occurred in MethodDeclaration to getParameters():->" + un.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return parameters;
    }

    private Map<String, String> getAllLocalVariablesInsideMethodDeclaration(MethodDeclaration md) {
        Map<String, String> usedVariables = new HashMap<String, String>();
        md.findAll(VariableDeclarator.class).forEach(V -> {
            try {
                usedVariables.put(V.getNameAsString(), V.getTypeAsString());
            } catch (UnsolvedSymbolException un) {
                System.err.println("Occurred in  VariableDeclarator:->" + un.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return usedVariables;
    }

    private List<MethodCall> getAllMethodCalls(MethodDeclaration md) {
        List<MethodCall> methodCallExpression = new ArrayList<MethodCall>();
        md.findAll(MethodCallExpr.class).forEach(m -> {
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
            } catch (UnsolvedSymbolException un) {
                System.err.println("Occurred in MethodCallExpr:-> " + un.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return methodCallExpression;
    }

    private List<ConstructorInvocation> getAllConstructorInvocations(MethodDeclaration md) {
        List<ConstructorInvocation> consInvocatino = new ArrayList<>();
        md.findAll(ObjectCreationExpr.class).forEach(obc -> {
            try {
                ResolvedConstructorDeclaration rCD = obc.resolve();
                String pakgName = rCD.getPackageName().trim();
                String constructorClassName = pakgName + "." + rCD.getClassName().trim();
                List<String> arguments = new ArrayList<>();
                obc.getArguments().forEach(ar -> {
                    arguments.add(ar.toString().trim());
                });
                consInvocatino.add(new ConstructorInvocation(constructorClassName, arguments));
            } catch (UnsolvedSymbolException un) {
                System.err.println("Occurred in ObjectCreationExpr:-> " + un.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return consInvocatino;
    }

    private static JavaSymbolSolver getConstructedJavaSymbolSolver(String rootDirectoryOfSource) {
        File[] directories = new File(rootDirectoryOfSource).listFiles(File::isDirectory);
        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        TypeSolver javaParserTypeSolver = new JavaParserTypeSolver(new File(rootDirectoryOfSource));
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
