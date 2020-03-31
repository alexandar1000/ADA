package com.ucl.ADA.model.class_structure;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.dependence_information.IncomingDependenceInfo;
import com.ucl.ADA.model.dependence_information.OutgoingDependenceInfo;
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
     * @param filePath name of the file, which is also file path
     * @param fileHash hash of the file
     * @return the new created class structure
     */
    public static ClassStructure initClassStructureWithFileNameAndFileHash(@NonNull String className, @NonNull String filePath, @NonNull String fileHash) {
        ClassStructure classStructure = new ClassStructure();
        classStructure.setClassName(className);
        classStructure.setFilePath(filePath);
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
            Map<String, IncomingDependenceInfo> incomingDependenceInfo = classStructure.getIncomingDependenceInfos();
            if (incomingToDeleteMap.containsKey(className)) {
                // if there are incoming dependence info to delete, query from incomingToDeleteMap
                Set<String> incomingToDeleteSet = incomingToDeleteMap.get(className);
                for (Map.Entry<String, IncomingDependenceInfo> entry : prevClassStructure.getIncomingDependenceInfos().entrySet()) {
                    String incomingClass = entry.getKey();
                    IncomingDependenceInfo dependenceInfo = entry.getValue();
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
        return m.getFilePath().equals(n.getFilePath()) && m.getFileHash().equals(n.getFileHash());
    }

    /* ************************************************************************
     *
     *  functions that update class structure with new information
     *
     **************************************************************************/

    /**
     * add a declaration element into the class structure depends on the declaration type
     *
     * @param classStructure  the class structure to update
     * @param declaration     the declaration object
     * @param declarationType the implemented type of declaration
     */
    public static void addDeclarationToClassStructure(@NonNull ClassStructure classStructure, @NonNull ElementDeclaration declaration, @NonNull DeclarationType declarationType) {
        switch (declarationType) {
            case CONSTRUCTOR:
                classStructure.addConstructorDeclaration((ConstructorDeclaration) declaration);
                break;
            case ATTRIBUTE:
                classStructure.addAttributeDeclaration((AttributeDeclaration) declaration);
                break;
            case METHOD:
                classStructure.addMethodDeclaration((MethodDeclaration) declaration);
                break;
            case IMPORT:
                classStructure.addImportDeclaration((ImportDeclaration) declaration);
                break;
            case PACKAGE:
                classStructure.setPackageDeclaration((PackageDeclaration) declaration);
                break;
            case PARAMETER:
                throw new IllegalArgumentException("parameter declaration should not be added into class structure");
            default:
                throw new IllegalArgumentException("Invalid declaration type");
        }
    }

    /**
     * add an internal invocation element as either an incoming or outgoing dependency into the class structure depends
     * on the invocation type
     *
     * @param classStructure      the class structure to update
     * @param relatingClass       the class which the invocation relates to
     * @param invocationDirection either an incoming invocation type in the case that the method is being invoked from
     *                            the relatingClass, or an outgoing invocation type in the case that the method is
     *                            invoked from the relatingClass
     * @param invocation          the invocation object
     * @param invocationType      the implemented type of invocation
     */
    public static void addInternalInvocationToClassStructure(@NonNull ClassStructure classStructure, @NonNull String relatingClass, @NonNull InvocationDirection invocationDirection, @NonNull InvocationType invocationType, @NonNull ElementInvocation invocation) {
        DependenceInfo dependenceInfo;
        // retrieve outgoing or incoming dependence info
        switch (invocationDirection) {
            case OUTGOING:
                if (!classStructure.getOutgoingDependenceInfos().containsKey(relatingClass)) {
                    classStructure.getOutgoingDependenceInfos().put(relatingClass, new OutgoingDependenceInfo());
                }
                dependenceInfo = classStructure.getOutgoingDependenceInfos().get(relatingClass);
                break;
            case INCOMING:
                if (!classStructure.getIncomingDependenceInfos().containsKey(relatingClass)) {
                    classStructure.getIncomingDependenceInfos().put(relatingClass, new IncomingDependenceInfo());
                }
                dependenceInfo = classStructure.getIncomingDependenceInfos().get(relatingClass);
                break;
            default:
                throw new IllegalArgumentException("Invalid invocation direction");
        }

        // add new invocation by its type
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

    /**
     * add an external invocation element into the class structure depends on the invocation type
     *
     * @param classStructure the class structure to update
     * @param invocationType the implemented type of invocation
     * @param invocation     the invocation object
     */
    public static void addExternalInvocationToClassStructure(@NonNull ClassStructure classStructure, @NonNull InvocationType invocationType, @NonNull ElementInvocation invocation) {
        switch (invocationType) {
            case METHOD:
                classStructure.addExternalMethodInvocations((MethodInvocation) invocation);
                break;
            case ATTRIBUTE:
                classStructure.addExternalAttributeInvocations((AttributeInvocation) invocation);
                break;
            case CONSTRUCTOR:
                classStructure.addExternalConstructorInvocations((ConstructorInvocation) invocation);
                break;
            default:
                throw new IllegalArgumentException("Invalid invocation type");
        }
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
