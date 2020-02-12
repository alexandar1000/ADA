package com.ucl.ADA.parser.extendedparser;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.utils.SourceRoot;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import com.ucl.ADA.parser.model.SourceFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JavaSourceParser {


    public static void main(String[] args) throws IOException {

        final String src_dir = "/home/mrhmisu/UCL-MS/Test-Project/src";
        //final String src_dir = "/home/mrhmisu/Downloads/Egami-master/src";
        List<CompilationUnit> cul = getAllCompilationUnits(src_dir);
        getParsedSource(cul);
    }

    private static List<CompilationUnit> getAllCompilationUnits(String path) throws IOException {
        // The directory where there is the code
        File baseDirectory = new File(path);
        // Configure the Symbol Solver
        CombinedTypeSolver typeSolver = getConstructedJavaSymbolSolver(path);
        // Use our Symbol Solver while parsing
        ParserConfiguration parserConfiguration = new ParserConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
        // Parse all source files
        SourceRoot sourceRoot = new SourceRoot(baseDirectory.toPath());
        sourceRoot.setParserConfiguration(parserConfiguration);
        List<ParseResult<CompilationUnit>> parseResults = sourceRoot.tryToParse("");
        // Now get all compilation units
        List<CompilationUnit> allCus = parseResults.stream()
                .filter(ParseResult::isSuccessful)
                .map(r -> r.getResult().get())
                .collect(Collectors.toList());

        return allCus;

    }

    private static Set<SourceFile> getParsedSource(List<CompilationUnit> allCus) {

        Set<SourceFile> sourceClasses = new HashSet<SourceFile>();

        allCus.forEach(cu -> {
            cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cl -> {

                ResolvedReferenceTypeDeclaration resolvedTypeForClass = cl.resolve();
                String packageName = "";
                packageName = resolvedTypeForClass.getPackageName();
                //System.out.println(packageName);
                // class name
                String className = resolvedTypeForClass.getQualifiedName();
                //System.out.println(className);

                //  extract the parents class interfaces,
                String parentClassName = "java.lang.Object";
                if (!cl.getExtendedTypes().isEmpty()) {
                    parentClassName = cl.getExtendedTypes().get(0).toString();
                }

                // implemented interfaces
                Set<String> implementedInterface = new HashSet<String>();
                cl.getImplementedTypes().forEach(a -> {
                    implementedInterface.add(a.toString());
                });


                // all decalared static fields
                Map<String, String> staticFields = new HashMap<String, String>();
                resolvedTypeForClass.getAllStaticFields().forEach(x -> {
                    staticFields.put(x.getName(), x.getType().describe());
                });






                // variables that have called methods.
                cl.findAll(VariableDeclarationExpr.class).forEach(v -> {
                    v.findAll(MethodCallExpr.class).forEach(mm -> {
                       // System.out.println(mm.resolve().getQualifiedName());

                    });


                  /*v.getVariables().forEach(K->{
                      System.out.print(K.getType()+" ");
                      System.out.print(K.getName()+"=");
                      System.out.print(K.getInitializer().get());
                      System.out.print(K.getInitializer().get());
                      K.getInitializer().get();
                      System.out.println();
                    });*/

                    //System.out.println(v.getElementType());

                });


                /*cl.findAll(MethodDeclaration.class).forEach(m->{

                    m.findAll(AssignExpr.class).forEach(a->{
                        System.out.println(a.getValue());
                        System.out.println(a.getTarget());
                    });



                });*/


               /* cl.resolve().getAllFields().forEach(vv->{

                     System.out.print(cl.getName()+"'->");System.out.print(vv.getName()+"=");
                    System.out.print(vv.getType());


                });*/

               /* cl.findAll(VariableDeclarationExpr.class).forEach(v->{
                    System.out.println(.toString());
                });*/


            });
        });

        return null;
    }


    private static CombinedTypeSolver getConstructedJavaSymbolSolver(String SRC_DIRECTORY_PATH) {
        File[] directories = new File(SRC_DIRECTORY_PATH).listFiles(File::isDirectory);
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
        return combinedSolver;
    }


}
