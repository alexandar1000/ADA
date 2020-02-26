package com.ucl.ADA.parser.parser;


import com.ucl.ADA.parser.model.*;
import com.ucl.ADA.parser.parser.visitor.VariableDeclarationVisitor;
import org.eclipse.jdt.core.dom.*;


import java.util.*;

public class JavaClassParser extends ASTVisitor {
    private String packageName = "";
    private Set<String> importedInternalClasses = new HashSet<>();
    private Set<String> importedExternalClasses = new HashSet<>();
    private String className = "";
    private String parentClassName = "";
    private Set<String> implementedInterfaces = new HashSet<>();
    private List<ADAClassAttribute> classAttributes = new ArrayList<>();

    private List<ADAMethodInvocation> ADAMethodInvocations = new ArrayList<>();
    private List<ADAConstructorInvocation> constructorInvocations = new ArrayList<>();
    private List<ADAMethodOrConstructorDeclaration> methodConstructorDeclaration = new ArrayList<>();
    private List<String> exMethodCalls = new ArrayList<>();
    private List<String> exConstructorInvocations = new ArrayList<>();
    private List<String> exFieldInvocation = new ArrayList<>();


    public JavaClassParser(String packageName, Set<String> importedInternalClasses, Set<String> importedExternalClasses) {

        this.packageName = packageName;
        this.importedInternalClasses.addAll(importedInternalClasses);
        this.importedExternalClasses.addAll(importedExternalClasses);
    }


    public ADAClass getExtractedClass() {
        ADAClass cl = new ADAClass(packageName, importedInternalClasses, importedExternalClasses, className, parentClassName, implementedInterfaces,
                classAttributes, ADAMethodInvocations, constructorInvocations, methodConstructorDeclaration, exMethodCalls, exConstructorInvocations, exFieldInvocation);

        return cl;
    }

    //variable declaration visitor
    private VariableDeclarationVisitor variableDeclaratorVisitor = new VariableDeclarationVisitor();


    //class name, parent class and implemented interfaces
    public boolean visit(TypeDeclaration typeDeclaration) {
        if (typeDeclaration.isPackageMemberTypeDeclaration()) {
            this.className = typeDeclaration.resolveBinding().getQualifiedName();
            if (typeDeclaration.getSuperclassType() != null) {
                this.parentClassName = typeDeclaration.getSuperclassType().resolveBinding().getQualifiedName();
            }
            List<Type> interfaces = typeDeclaration.superInterfaceTypes();
            for (Type anInterface : interfaces) {
                implementedInterfaces.add(anInterface.resolveBinding().getQualifiedName());
            }
        }
        return true;
    }

    // class attributes
    public boolean visit(FieldDeclaration node) {
        for (Iterator iter = node.fragments().iterator(); iter.hasNext(); ) {
            VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter.next();
            IVariableBinding binding = fragment.resolveBinding();
            if (binding != null) {
                Set<String> modifiers = new HashSet<>();
                List<Object> mlist = node.modifiers();
                for (Object o : mlist) {
                    IExtendedModifier im = (IExtendedModifier) o;
                    if (im.isAnnotation()) {
                        Annotation mk = (Annotation) o;
                        modifiers.add(mk.toString());
                    } else {
                        Modifier md = (Modifier) o;
                        modifiers.add(md.getKeyword().toString());
                    }
                }
                String name = binding.getName();
                String type = binding.getType().getQualifiedName();
                String value = "";
                if (fragment.getInitializer() != null) {
                    //System.out.println(fragment.getInitializer().toString());
                    value = fragment.getInitializer().toString();
                }
                ADAClassAttribute sa = new ADAClassAttribute(modifiers, name, type, value);
                this.classAttributes.add(sa);
                //System.out.println(binding.getName() + "--" + binding.getType().getQualifiedName());
            } else {
                this.exFieldInvocation.add(fragment.getNodeType() + "");
            }

        }
        return true;
    }

    // method invocations
    public boolean visit(MethodInvocation node) {
        IMethodBinding binding = node.resolveMethodBinding();
        String methodCallName = "";
        String calleeName = "";
        List<String> arguments = new ArrayList<>();
        if (binding != null) {
            if (!binding.isConstructor()) {
                methodCallName = node.getName().toString();
                calleeName = binding.getDeclaringClass().getQualifiedName();
                List<ASTNode> list = node.arguments();
                if (!list.isEmpty()) {
                    for (ASTNode an : list) {
                        //System.out.println(an.toString());
                        //int type=an.getNodeType();
                        //ASTNode.nodeClassForType(an.getNodeType());
                        arguments.add(an.toString());
                    }
                }

                //System.out.println(binding.getName() + "-->" + binding.getDeclaringClass().getQualifiedName());
            }
            this.ADAMethodInvocations.add(new ADAMethodInvocation(methodCallName, calleeName, arguments));
        } else {
            this.exMethodCalls.add(node.getName().toString());
            //System.out.println(node.getName().toString());
        }
        return true;
    }

    // constructor invocation.
    public boolean visit(ClassInstanceCreation node) {
        IMethodBinding binding = node.resolveConstructorBinding();
        String name = "";
        List<String> arguments = new ArrayList<>();
        if (binding != null) {
            if (binding.isConstructor()) {
                name = binding.getDeclaringClass().getQualifiedName();
                List<ASTNode> list = node.arguments();
                if (!list.isEmpty()) {
                    for (ASTNode an : list) {
                        //System.out.println(an.toString());
                        //int type=an.getNodeType();
                        //ASTNode.nodeClassForType(an.getNodeType());
                        arguments.add(an.toString());
                    }
                }
                this.constructorInvocations.add(new ADAConstructorInvocation(name, arguments));
            }

        } else {
            this.exConstructorInvocations.add(node.toString());
            //System.out.println(node.toString());
        }
        return true;
    }

    // method or constructor declaration
    public boolean visit(MethodDeclaration node) {
        String name = node.getName().toString();
        String returnType = "";
        if (node.getReturnType2() != null) {
            returnType = node.getReturnType2().toString();
        }

        Map<String, String> parameters = new HashMap<>();
        List<SingleVariableDeclaration> list = node.parameters();
        if (!list.isEmpty()) {
            for (SingleVariableDeclaration sd : list) {
                String parameterName = sd.getName().toString();
                String parameterType = sd.getType().toString();
                parameters.put(parameterName, parameterType);
            }
        }

        Set<String> accessModifiers = new HashSet<>();
        List<Object> mlist = node.modifiers();
        for (Object o : mlist) {
            IExtendedModifier im = (IExtendedModifier) o;
            if (im.isAnnotation()) {
                Annotation mk = (Annotation) o;
                accessModifiers.add(mk.toString());
            } else {
                Modifier md = (Modifier) o;
                accessModifiers.add(md.getKeyword().toString());
            }
        }
        Map<String, String> localVariables = new HashMap<>();
        node.accept(this.variableDeclaratorVisitor);
        localVariables.putAll(this.variableDeclaratorVisitor.getLocalVariables());
        if (node.isConstructor()) {
            boolean isConstructor = true;
            //name,returnType,accessModifiers,parameters,localVariables,isConstructor
            ADAMethodOrConstructorDeclaration mc = new ADAMethodOrConstructorDeclaration(name, returnType, accessModifiers, parameters, localVariables, isConstructor);
            this.methodConstructorDeclaration.add(mc);
        } else {
            boolean isConstructor = false;
            ADAMethodOrConstructorDeclaration mc = new ADAMethodOrConstructorDeclaration(name, returnType, accessModifiers, parameters, localVariables, isConstructor);
            this.methodConstructorDeclaration.add(mc);
        }
        return true;
    }

}
