package com.ucl.ADA.model.class_structure;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.static_information.declaration_information.*;
import lombok.NonNull;

import java.util.Map;
import java.util.Set;

public class ClassStructureUtils {

    /* ************************************************************************
     *
     *  functions that initialize class structure
     *
     **************************************************************************/

    /**
     * initialize a new class structure given the file name and file hash
     *
     * @param fileName name of the file, which is also file path
     * @param fileHash hash of the file
     * @return the new created class structure
     */
    public static ClassStructure initClassStructureWithFileNameAndFileHash(@NonNull String className, @NonNull String fileName, @NonNull String fileHash) {
        ClassStructure classStructure = new ClassStructure();
        classStructure.setClassName(className);
        classStructure.setFileName(fileName);
        classStructure.setFileHash(fileHash);
        return classStructure;
    }

    /* ************************************************************************
     *
     *  functions that reuse another class structure
     *
     **************************************************************************/

    /**
     * generate a class structure given initialized class structure, the previous class structure to reuse, information
     * to reuse static info and incoming dependence info
     *
     * @param classStructure      the initialized class structure
     * @param prevClassStructure  the class structure exists in previous snapshot that has same qualified name
     * @param incomingToDeleteMap a guava SetMultimap object that can be viewed as Map<String, Set<String>>. The key is
     *                            the name of an affected class structure, while the value is a set of class names
     *                            should be deleted from affected class structure's incoming dependence info
     * @param incomingToAddSet    a set of class names of all class who has new incoming dependence info
     * @return a class structure which reuse (at least parts of) previous class structure
     */
    public static ClassStructure reuseClassStructure(@NonNull ClassStructure classStructure, ClassStructure prevClassStructure, @NonNull SetMultimap<String, String> incomingToDeleteMap, @NonNull Set<String> incomingToAddSet) {
        // if previous class structure is null, then nothing to reuse
        if (prevClassStructure == null) {
            return classStructure;
        }
        // if the class names are different, then should not be reused
        if (!classStructure.getClassName().equals(prevClassStructure.getClassName())) {
            throw new IllegalArgumentException("initialized and previous class structure must have the same class name");
        }

        String className = classStructure.getClassName();

        // check which parts of class structure can be reused
        boolean reuseStaticInfo = isClassStaticInfoEqual(classStructure, prevClassStructure);
        boolean reuseIncomingDependenceInfo = isClassIncomingUnchanged(className, incomingToDeleteMap, incomingToAddSet);

        // if both static info and incoming dependence info are unchanged,
        // then reuse the previous class structure object
        if (reuseStaticInfo && reuseIncomingDependenceInfo) {
            return prevClassStructure;
        }

        // if cannot reuse the class structure object,
        // then reuse static info and incoming dependence info if available
        if (reuseStaticInfo) {
            classStructure.setStaticInfo(prevClassStructure.getStaticInfo());
        }
        if (reuseIncomingDependenceInfo) {
            // reuse all incoming dependence info if available
            classStructure.setIncomingDependenceInfos(prevClassStructure.getIncomingDependenceInfos());
        } else {
            // reuse incoming dependence info excludes incomingToDeleteMap from previous class structure
            Map<String, DependenceInfo> incomingDependenceInfo = classStructure.getIncomingDependenceInfos();
            if (incomingToDeleteMap.containsKey(className)) {
                // if there are incoming dependence info to delete, query from incomingToDeleteMap
                Set<String> incomingToDeleteSet = incomingToDeleteMap.get(className);
                for (Map.Entry<String, DependenceInfo> entry : prevClassStructure.getIncomingDependenceInfos().entrySet()) {
                    String incomingClass = entry.getKey();
                    DependenceInfo dependenceInfo = entry.getValue();
                    if (!incomingToDeleteSet.contains(incomingClass)) {
                        incomingDependenceInfo.put(incomingClass, dependenceInfo);
                    }
                }
            } else {
                // if there is no deletion in incomingToDelete, reuse all incoming dependence info from previous class structure
                incomingDependenceInfo.putAll(prevClassStructure.getIncomingDependenceInfos());
            }
        }
        return classStructure;
    }

    /**
     * check if the incoming dependence info of the class is unchanged by searching it from two collections of changed
     * incoming dependence info
     *
     * @param className           qualified class name
     * @param incomingToDeleteMap a guava SetMultimap object that can be viewed as Map<String, Set<String>>. The key is
     *                            the name of an affected class structure, while the value is a set of class names
     *                            should be deleted from affected class structure's incoming dependence info
     * @param incomingToAddSet    a set of class names of all class who has new incoming dependence info
     * @return true only if the class has no incoming dependence info change
     */
    public static boolean isClassIncomingUnchanged(@NonNull String className, @NonNull SetMultimap<String, String> incomingToDeleteMap, @NonNull Set<String> incomingToAddSet) {
        return !incomingToAddSet.contains(className) && !incomingToDeleteMap.containsKey(className);
    }

    /**
     * check if two class structures have the same static info by comparing the file name and file hash
     *
     * @param m the first input class structure object
     * @param n the second input class structure object
     * @return true only if two class structures have the same static info
     */
    public static boolean isClassStaticInfoEqual(@NonNull ClassStructure m, @NonNull ClassStructure n) {
        if (!m.getClassName().equals(n.getClassName())) {
            throw new IllegalArgumentException("the two input class structures must have the same class name");
        }
        return m.getFileName().equals(n.getFileName()) && m.getFileHash().equals(n.getFileHash());
    }

    /* ************************************************************************
     *
     *  functions that add declarations of class structure
     *
     **************************************************************************/

    /**
     * Updates the package corresponding to the class.
     *
     * @param classStructure     the class structure to update
     * @param packageDeclaration the name of the package corresponding to the current class
     */
    public static void setCurrentPackage(ClassStructure classStructure, PackageDeclaration packageDeclaration) {
        classStructure.getStaticInfo().setCurrentPackage(packageDeclaration);
    }

    /**
     * Adds an import declaration corresponding to the class.
     *
     * @param classStructure    the class structure to update
     * @param importDeclaration the import declaration object
     */
    public static void addImportDeclaration(ClassStructure classStructure, ImportDeclaration importDeclaration) {
        classStructure.getStaticInfo().getImportDeclarations().add(importDeclaration);
    }

    /**
     * Adds a Attribute Declaration to the ClassDependenceTree
     *
     * @param classStructure       the class structure to update
     * @param attributeDeclaration the attribute declaration object
     */
    public static void addAttributeDeclaration(ClassStructure classStructure, AttributeDeclaration attributeDeclaration) {
        classStructure.getStaticInfo().getAttributeDeclarations().add(attributeDeclaration);
    }

    /**
     * Adds a Method Declaration to the ClassDependenceTree
     *
     * @param classStructure    the class structure to update
     * @param methodDeclaration the method declaration object
     */
    public static void addMethodDeclaration(ClassStructure classStructure, MethodDeclaration methodDeclaration) {
        classStructure.getStaticInfo().getMethodsDeclarations().add(methodDeclaration);
    }

    /**
     * Adds a Constructor Declaration to the ClassDependenceTree
     *
     * @param classStructure         the class structure to update
     * @param constructorDeclaration the constructor declaration object
     */
    public static void addConstructorDeclaration(ClassStructure classStructure, ConstructorDeclaration constructorDeclaration) {
        classStructure.getStaticInfo().getConstructorDeclarations().add(constructorDeclaration);
    }

    /* ************************************************************************
     *
     *  functions that add internal invocations of class structure
     *
     **************************************************************************/

    /**
     * Adds an attribute invocation element as either an incoming or outgoing dependency, depending on the call.
     *
     * @param classStructure      the class structure to update
     * @param relatingClass       the class which the invocation relates to
     * @param invocationDirection either an incoming invocation type in the case that the attribute is being invoked
     *                            from the relatingClass, or an outgoing invocation type in the case that the attribute
     *                            is invoked from the relatingClass
     * @param attributeInvocation the attribute invocation object containing the data corresponding to the invocation in
     *                            question
     */
    public static void addAttributeInvocation(ClassStructure classStructure, String relatingClass, InvocationDirection invocationDirection, AttributeInvocation attributeInvocation) {
        if (invocationDirection == InvocationDirection.OUTGOING) {
            Map<String, DependenceInfo> outgoingDependenceInfos = classStructure.getStaticInfo().getOutgoingDependenceInfos();
            if (outgoingDependenceInfos.containsKey(relatingClass)) {
                outgoingDependenceInfos.get(relatingClass).addAttributeInvocation(attributeInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addAttributeInvocation(attributeInvocation);
                outgoingDependenceInfos.put(relatingClass, dependenceInfo);
            }
        } else {
            Map<String, DependenceInfo> incomingDependenceInfos = classStructure.getIncomingDependenceInfos();
            if (incomingDependenceInfos.containsKey(relatingClass)) {
                incomingDependenceInfos.get(relatingClass).addAttributeInvocation(attributeInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addAttributeInvocation(attributeInvocation);
                incomingDependenceInfos.put(relatingClass, dependenceInfo);
            }
        }
    }

    /**
     * Adds a constructor invocation element as either an incoming or outgoing dependency, depending on the call.
     *
     * @param classStructure        the class structure to update
     * @param relatingClass         the class which the invocation relates to
     * @param invocationDirection   either an incoming invocation type in the case that the constructor is being invoked
     *                              from the relatingClass, or an outgoing invocation type in the case that the
     *                              constructor is invoked from the relatingClass
     * @param constructorInvocation the constructor invocation object containing the data corresponding to the
     *                              invocation in question
     */
    public static void addConstructorInvocation(ClassStructure classStructure, String relatingClass, InvocationDirection invocationDirection, ConstructorInvocation constructorInvocation) {
        if (invocationDirection == InvocationDirection.OUTGOING) {
            Map<String, DependenceInfo> outgoingDependenceInfos = classStructure.getStaticInfo().getOutgoingDependenceInfos();
            if (outgoingDependenceInfos.containsKey(relatingClass)) {
                outgoingDependenceInfos.get(relatingClass).addConstructorInvocation(constructorInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addConstructorInvocation(constructorInvocation);
                outgoingDependenceInfos.put(relatingClass, dependenceInfo);
            }
        } else {
            Map<String, DependenceInfo> incomingDependenceInfos = classStructure.getIncomingDependenceInfos();
            if (incomingDependenceInfos.containsKey(relatingClass)) {
                incomingDependenceInfos.get(relatingClass).addConstructorInvocation(constructorInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addConstructorInvocation(constructorInvocation);
                incomingDependenceInfos.put(relatingClass, dependenceInfo);
            }
        }
    }

    /**
     * Adds a method invocation element as either an incoming or outgoing dependency, depending on the call.
     *
     * @param classStructure      the class structure to update
     * @param relatingClass       the class which the invocation relates to
     * @param invocationDirection either an incoming invocation type in the case that the method is being invoked from
     *                            the relatingClass, or an outgoing invocation type in the case that the method is
     *                            invoked from the relatingClass
     * @param methodInvocation    the method invocation object containing the data corresponding to the invocation in
     *                            question
     */
    public static void addMethodInvocation(ClassStructure classStructure, String relatingClass, InvocationDirection invocationDirection, MethodInvocation methodInvocation) {
        if (invocationDirection == InvocationDirection.OUTGOING) {
            Map<String, DependenceInfo> outgoingDependenceInfos = classStructure.getStaticInfo().getOutgoingDependenceInfos();
            if (outgoingDependenceInfos.containsKey(relatingClass)) {
                outgoingDependenceInfos.get(relatingClass).addMethodInvocation(methodInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addMethodInvocation(methodInvocation);
                outgoingDependenceInfos.put(relatingClass, dependenceInfo);
            }
        } else {
            Map<String, DependenceInfo> incomingDependenceInfos = classStructure.getIncomingDependenceInfos();
            if (incomingDependenceInfos.containsKey(relatingClass)) {
                incomingDependenceInfos.get(relatingClass).addMethodInvocation(methodInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addMethodInvocation(methodInvocation);
                incomingDependenceInfos.put(relatingClass, dependenceInfo);
            }
        }
    }

    public static void addInvocation(ClassStructure classStructure, String relatingClass, InvocationDirection invocationDirection, ElementInvocation invocation, InvocationType invocationType) {
        Map<String, DependenceInfo> dependenceInfos;
        switch (invocationDirection) {
            case OUTGOING:
                dependenceInfos = classStructure.getStaticInfo().getOutgoingDependenceInfos();
                break;
            case INCOMING:
                dependenceInfos = classStructure.getIncomingDependenceInfos();
                break;
            default:
                throw new IllegalArgumentException("Invalid invocation direction");
        }
        if (!dependenceInfos.containsKey(relatingClass)) {
            dependenceInfos.put(relatingClass, new DependenceInfo());
        }
        DependenceInfo dependenceInfo = dependenceInfos.get(relatingClass);
        switch (invocationType) {
            case METHOD:
                dependenceInfo.addMethodInvocation((MethodInvocation) invocation);
                break;
            case ATTRIBUTE:
                dependenceInfo.addAttributeInvocation((AttributeInvocation) invocation);
                break;
            case CONSTRUCTOR:
                dependenceInfo.addConstructorInvocation((ConstructorInvocation) invocation);
                break;
            default:
                throw new IllegalArgumentException("Invalid invocation type");
        }
    }

    /* ************************************************************************
     *
     *  functions that add external invocations of class structure
     *
     **************************************************************************/

    /**
     * Adds a new external attribute invocation
     *
     * @param classStructure      the class structure to update
     * @param attributeInvocation an external attribute invocation object containing all of the related information
     *                            about the attribute invocation being added
     */
    public static void addExternalAttributeInvocation(ClassStructure classStructure, AttributeInvocation
            attributeInvocation) {
        classStructure.getStaticInfo().getExternalAttributeInvocations().add(attributeInvocation);
    }

    /**
     * Adds a new external constructor invocation
     *
     * @param classStructure        the class structure to update
     * @param constructorInvocation an external constructor invocation object containing all of the related information
     *                              about the constructor invocation being added
     */
    public static void addExternalConstructorInvocation(ClassStructure classStructure, ConstructorInvocation
            constructorInvocation) {
        classStructure.getStaticInfo().getExternalConstructorInvocations().add(constructorInvocation);
    }

    /**
     * Adds a new external method invocation
     *
     * @param classStructure   the class structure to update
     * @param methodInvocation an external method invocation object containing all of the related information about the
     *                         method invocation being added
     */
    public static void addExternalMethodInvocation(ClassStructure classStructure, MethodInvocation methodInvocation) {
        classStructure.getStaticInfo().getExternalMethodInvocations().add(methodInvocation);
    }

    /* ************************************************************************
     *
     *  functions that compute metrics of class structure
     *
     **************************************************************************/

//    public static void computeClassMetric(ClassMetricType classMetricType) {
//        float metricValue = 0F;
//        // TODO: This could be made nicer by extracting the for loop around the switch. However, the switch would then
//        //  be executed for each item, which would make it less efficient.
//        Collection<DependenceInfo> incomingDependencyValues = incomingDependenceInfos.values();
//        Collection<DependenceInfo> outgoingDependencyValues = outgoingDependenceInfos.values();
//
//        switch (classMetricType) {
//            case NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_INCOMING:
//                for (DependenceInfo dependenceInfoValue : incomingDependencyValues) {
//                    metricValue += dependenceInfoValue.getAttributes().size();
//                }
//                this.classMetricValues.setNumberOfAttributeInvocationsIncoming(metricValue);
//                break;
//
//            case NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_OUTGOING:
//                for (DependenceInfo dependenceInfoValue : outgoingDependencyValues) {
//                    metricValue += dependenceInfoValue.getAttributes().size();
//                }
//                this.classMetricValues.setNumberOfAttributeInvocationsOutgoing(metricValue);
//                break;
//
//            case NUMBER_OF_CLASS_METHOD_INVOCATIONS_INCOMING:
//                for (DependenceInfo dependenceInfoValue : incomingDependencyValues) {
//                    metricValue += dependenceInfoValue.getMethods().size();
//                }
//                this.classMetricValues.setNumberOfMethodInvocationsIncoming(metricValue);
//                break;
//
//            case NUMBER_OF_CLASS_METHOD_INVOCATIONS_OUTGOING:
//                for (DependenceInfo dependenceInfoValue : outgoingDependencyValues) {
//                    metricValue += dependenceInfoValue.getMethods().size();
//                }
//                this.classMetricValues.setNumberOfMethodInvocationsOutgoing(metricValue);
//                break;
//
//            case NUMBER_OF_CLASS_PACKAGE_IMPORTS_INCOMING:
//                for (DependenceInfo dependenceInfoValue : incomingDependencyValues) {
//                    metricValue += dependenceInfoValue.getPackages().size();
//                }
//                this.classMetricValues.setNumberOfPackageImportsIncoming(metricValue);
//                break;
//
//            case NUMBER_OF_CLASS_PACKAGE_IMPORTS_OUTGOING:
//                for (DependenceInfo dependenceInfoValue : outgoingDependencyValues) {
//                    metricValue += dependenceInfoValue.getPackages().size();
//                }
//                this.classMetricValues.setNumberOfPackageImportsOutgoing(metricValue);
//                break;
//
//            case NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_INCOMING:
//                for (DependenceInfo dependenceInfoValue : incomingDependencyValues) {
//                    metricValue += dependenceInfoValue.getConstructors().size();
//                }
//                this.classMetricValues.setNumberOfConstructorInvocationsIncoming(metricValue);
//                break;
//
//            case NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_OUTGOING:
//                for (DependenceInfo dependenceInfoValue : outgoingDependencyValues) {
//                    metricValue += dependenceInfoValue.getConstructors().size();
//                }
//                this.classMetricValues.setNumberOfConstructorInvocationsOutgoing(metricValue);
//                break;
//
//            case BIDIRECTIONAL_NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS:
//                for (DependenceInfo dependenceInfoValue : incomingDependencyValues) {
//                    metricValue += dependenceInfoValue.getAttributes().size();
//                }
//                for (DependenceInfo dependenceInfoValue : outgoingDependencyValues) {
//                    metricValue += dependenceInfoValue.getAttributes().size();
//                }
//                this.classMetricValues.setBidirectionalNumberOfAttributeInvocations(metricValue);
//                break;
//
//            case BIDIRECTIONAL_NUMBER_OF_CLASS_METHOD_INVOCATIONS:
//                for (DependenceInfo dependenceInfoValue : incomingDependencyValues) {
//                    metricValue += dependenceInfoValue.getMethods().size();
//                }
//                for (DependenceInfo dependenceInfoValue : outgoingDependencyValues) {
//                    metricValue += dependenceInfoValue.getMethods().size();
//                }
//                this.classMetricValues.setBidirectionalNumberOfMethodInvocations(metricValue);
//                break;
//
//            case BIDIRECTIONAL_NUMBER_OF_CLASS_PACKAGE_IMPORTS:
//                for (DependenceInfo dependenceInfoValue : incomingDependencyValues) {
//                    metricValue += dependenceInfoValue.getPackages().size();
//                }
//                for (DependenceInfo dependenceInfoValue : outgoingDependencyValues) {
//                    metricValue += dependenceInfoValue.getPackages().size();
//                }
//                this.classMetricValues.setBidirectionalNumberOfPackageImports(metricValue);
//                break;
//
//            case BIDIRECTIONAL_NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS:
//                for (DependenceInfo dependenceInfoValue : incomingDependencyValues) {
//                    metricValue += dependenceInfoValue.getConstructors().size();
//                }
//                for (DependenceInfo dependenceInfoValue : outgoingDependencyValues) {
//                    metricValue += dependenceInfoValue.getConstructors().size();
//                }
//                this.classMetricValues.setBidirectionalNumberOfConstructorInvocations(metricValue);
//                break;
//        }
//    }
//
//    /**
//     * compute relation metric which collects detailed invocation information from dependence info
//     *
//     * @param relationMetricType outgoing or incoming relation metric
//     */
//    public static void computeRelationMetric(RelationMetricType relationMetricType) {
//        float metricValue = 0F;
//        // TODO: This could be made nicer by extracting the for loop around the switch. However, the switch would then
//        //  be executed for each item, which would make it less efficient.
//
//        switch (relationMetricType) {
//            case NUMBER_OF_RELATION_PACKAGE_IMPORTS_INCOMING:
//
//                // For all of the relating classes get the corresponding metrics
//                for (String key : incomingDependenceInfos.keySet()) {
//                    metricValue = (float) incomingDependenceInfos.get(key).getPackages().size();
//
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setNumberOfPackageImportsIncoming(metricValue);
//                }
//
//                break;
//
//            case NUMBER_OF_RELATION_PACKAGE_IMPORTS_OUTGOING:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : outgoingDependenceInfos.keySet()) {
//                    metricValue = (float) outgoingDependenceInfos.get(key).getPackages().size();
//
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setNumberOfPackageImportsOutgoing(metricValue);
//                }
//                break;
//
//            case NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_INCOMING:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : incomingDependenceInfos.keySet()) {
//                    metricValue = (float) incomingDependenceInfos.get(key).getAttributes().size();
//
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setNumberOfAttributeInvocationsIncoming(metricValue);
//                }
//                break;
//
//
//            case NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_OUTGOING:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : outgoingDependenceInfos.keySet()) {
//                    metricValue = (float) outgoingDependenceInfos.get(key).getAttributes().size();
//
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setNumberOfAttributeInvocationsOutgoing(metricValue);
//                }
//                break;
//
//            case NUMBER_OF_RELATION_METHOD_INVOCATIONS_INCOMING:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : incomingDependenceInfos.keySet()) {
//                    metricValue = (float) incomingDependenceInfos.get(key).getMethods().size();
//
//
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setNumberOfMethodInvocationsIncoming(metricValue);
//                }
//                break;
//
//            case NUMBER_OF_RELATION_METHOD_INVOCATIONS_OUTGOING:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : outgoingDependenceInfos.keySet()) {
//                    metricValue = (float) outgoingDependenceInfos.get(key).getMethods().size();
//
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setNumberOfMethodInvocationsOutgoing(metricValue);
//                }
//                break;
//
//
//            case NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_INCOMING:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : incomingDependenceInfos.keySet()) {
//                    metricValue = (float) incomingDependenceInfos.get(key).getConstructors().size();
//
//
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setNumberOfConstructorInvocationsIncoming(metricValue);
//                }
//                break;
//
//            case NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_OUTGOING:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : outgoingDependenceInfos.keySet()) {
//                    metricValue = (float) outgoingDependenceInfos.get(key).getConstructors().size();
//
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setNumberOfConstructorInvocationsOutgoing(metricValue);
//                }
//                break;
//
//            case BIDIRECTIONAL_NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : outgoingDependenceInfos.keySet()) {
//                    metricValue = (float) outgoingDependenceInfos.get(key).getAttributes().size();
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setBidirectionalNumberOfAttributeInvocations(metricValue);
//                }
//                for (String key : incomingDependenceInfos.keySet()) {
//                    metricValue = (float) incomingDependenceInfos.get(key).getAttributes().size();
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfAttributeInvocations();
//                    relationMetricValues.get(key).setBidirectionalNumberOfAttributeInvocations(currentMetricValue + metricValue);
//                }
//                break;
//
//            case BIDIRECTIONAL_NUMBER_OF_RELATION_METHOD_INVOCATIONS:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : outgoingDependenceInfos.keySet()) {
//                    metricValue = (float) outgoingDependenceInfos.get(key).getMethods().size();
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setBidirectionalNumberOfMethodInvocations(metricValue);
//                }
//                for (String key : incomingDependenceInfos.keySet()) {
//                    metricValue = (float) incomingDependenceInfos.get(key).getMethods().size();
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfMethodInvocations();
//                    relationMetricValues.get(key).setBidirectionalNumberOfMethodInvocations(currentMetricValue + metricValue);
//                }
//                break;
//
//            case BIDIRECTIONAL_NUMBER_OF_RELATION_PACKAGE_IMPORTS:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : outgoingDependenceInfos.keySet()) {
//                    metricValue = (float) outgoingDependenceInfos.get(key).getPackages().size();
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setBidirectionalNumberOfPackageImports(metricValue);
//                }
//                for (String key : incomingDependenceInfos.keySet()) {
//                    metricValue = (float) incomingDependenceInfos.get(key).getPackages().size();
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    float previousMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfPackageImports();
//                    relationMetricValues.get(key).setBidirectionalNumberOfPackageImports(previousMetricValue + metricValue);
//                }
//                break;
//
//            case BIDIRECTIONAL_NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS:
//                // For all of the relating classes get the corresponding metrics
//                for (String key : outgoingDependenceInfos.keySet()) {
//                    metricValue = (float) outgoingDependenceInfos.get(key).getConstructors().size();
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    relationMetricValues.get(key).setBidirectionalNumberOfConstructorInvocations(metricValue);
//                }
//                for (String key : incomingDependenceInfos.keySet()) {
//                    metricValue = (float) incomingDependenceInfos.get(key).getConstructors().size();
//                    // Check if the relation metrics for the class have already been computed
//                    if (!relationMetricValues.containsKey(key)) {
//                        RelationMetricValue relationMetricValueObject = new RelationMetricValue();
//                        relationMetricValues.put(key, relationMetricValueObject);
//                    }
//                    float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfConstructorInvocations();
//                    relationMetricValues.get(key).setBidirectionalNumberOfConstructorInvocations(currentMetricValue + metricValue);
//                }
//                break;
//        }
//    }
//
//    /**
//     * compute class metrics for all class metric type
//     */
//    public static void computeAllClassMetrics() {
//        for (ClassMetricType classMetricType : ClassMetricType.values()) {
//            this.computeClassMetric(classMetricType);
//        }
//    }
//
//    /**
//     * compute relation metrics for all relation types
//     */
//    public static void computeAllRelationMetrics() {
//        for (RelationMetricType relationMetricType : RelationMetricType.values()) {
//            this.computeRelationMetric(relationMetricType);
//        }
//    }
}
