package com.ucl.ADA.parser.parser.visitor;


import com.ucl.ADA.parser.ada_model.*;
import lombok.Getter;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import java.util.*;


@Getter
public class ADAClassVisitor extends ASTVisitor {
    private String packageName = "";
    private Set<String> importedInternalClasses = new HashSet<>();
    private Set<String> importedExternalClasses = new HashSet<>();
    private String className = "";
    private boolean isInterface = false;
    private boolean isEnum = false;
    private String parentClassName = "";
    private Set<String> implementedInterfaces = new HashSet<>();
    private List<ADAClassAttribute> classAttributes = new ArrayList<>();
    private List<String> declaredEnums = new ArrayList<>();

    private List<ADAMethodInvocation> ADAMethodInvocations = new ArrayList<>();
    private List<ADAConstructorInvocation> constructorInvocations = new ArrayList<>();
    private List<ADAMethodOrConstructorDeclaration> methodConstructorDeclaration = new ArrayList<>();
    private List<String> exMethodCalls = new ArrayList<>();
    private List<String> exConstructorInvocations = new ArrayList<>();
    private List<String> exFieldInvocation = new ArrayList<>();


    /**
     * A constructor of ADAClassVisitor
     *
     * @param packageName             Packages name of the Class
     * @param importedInternalClasses Imported internal classes that are declared in the project
     * @param importedExternalClasses Imported external classes that are utilized from the external libraries.
     */
    public ADAClassVisitor(String packageName, Set<String> importedInternalClasses, Set<String> importedExternalClasses) {

        this.packageName = packageName;
        this.importedInternalClasses.addAll(importedInternalClasses);
        this.importedExternalClasses.addAll(importedExternalClasses);
    }


    /**
     * This creates an ADAClass model using the parsed information.
     *
     * @return A ADAClass model containing the extracted class information.
     */
    public ADAClass getExtractedClass() {
        ADAClass cl = new ADAClass(packageName, importedInternalClasses, importedExternalClasses, className, isInterface, isEnum, parentClassName, implementedInterfaces,
                classAttributes, declaredEnums, ADAMethodInvocations, constructorInvocations, methodConstructorDeclaration, exMethodCalls, exConstructorInvocations, exFieldInvocation);

        return cl;
    }

    private VariableDeclarationVisitor variableDeclaratorVisitor = new VariableDeclarationVisitor();


    /**
     * It visits the TypeDeclaration node from the AST and
     * retrieves the required information such as class name, parent class name and implemented interface,
     *
     * @param node A TypeDeclaration node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
    public boolean visit(TypeDeclaration node) {
        if (node.isPackageMemberTypeDeclaration()) {
            if (node.isInterface()) {
                this.isInterface = true;
            }
            this.className = node.resolveBinding().getQualifiedName();
            if (node.getSuperclassType() != null) {
                this.parentClassName = node.getSuperclassType().resolveBinding().getQualifiedName();
            }
            List<Type> interfaces = node.superInterfaceTypes();
            for (Type anInterface : interfaces) {
                implementedInterfaces.add(anInterface.resolveBinding().getQualifiedName());
            }
        }
        return true;
    }

    /**
     * It visits the EnumDeclaration node from the AST and
     * retrieves the required information to populate EnumConstantDeclaration model
     *
     * @param node A EnumDeclaration node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
    public boolean visit(EnumDeclaration node) {
        if (node.isPackageMemberTypeDeclaration()) {
            this.isEnum = true;
            this.className = node.resolveBinding().getQualifiedName();
            List<Type> interfaces = node.superInterfaceTypes();
            for (Type anInterface : interfaces) {
                implementedInterfaces.add(anInterface.resolveBinding().getQualifiedName());
            }
            List<ASTNode> enumConstant = node.enumConstants();
            if (!enumConstant.isEmpty()) {
                for (ASTNode an : enumConstant) {
                    if (an instanceof EnumConstantDeclaration) {
                        EnumConstantDeclaration en = (EnumConstantDeclaration) an;
                        declaredEnums.add(en.getName().toString());
                    }

                }
            }
        }
        return true;
    }

    /**
     * It visits the FieldDeclaration node from the AST and
     * retrieves the required information to populate ADAClassAttribute model
     *
     * @param node A FieldDeclaration node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
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
                    value = fragment.getInitializer().toString();
                }
                ADAClassAttribute sa = new ADAClassAttribute(modifiers, name, type, value);
                this.classAttributes.add(sa);
            } else {
                this.exFieldInvocation.add(fragment.getNodeType() + "");
            }
        }
        return true;
    }

    /**
     * It visits the MethodInvocation node from the AST and
     * retrieves the required information to populate ADAMethodInvocation model
     *
     * @param node A MethodInvocation node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
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
                        arguments.add(an.toString());
                    }
                }
            }
            this.ADAMethodInvocations.add(new ADAMethodInvocation(methodCallName, calleeName, arguments));
        } else {
            this.exMethodCalls.add(node.getName().toString());
        }
        return true;
    }

    /**
     * It visits the ClassInstanceCreation node from the AST and
     * retrieves the required information to populate ADAConstructorInvocation model
     *
     * @param node A ClassInstanceCreation node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
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
                        arguments.add(an.toString());
                    }
                }
                this.constructorInvocations.add(new ADAConstructorInvocation(name, arguments));
            }
        } else {
            this.exConstructorInvocations.add(node.toString());
        }
        return true;
    }

    /**
     * This method visits the MethodDeclaration node from the AST and
     * retrieves the required information to populate ADAMethodOrConstructorDeclaration model
     *
     * @param node A MethodDeclaration node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
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
