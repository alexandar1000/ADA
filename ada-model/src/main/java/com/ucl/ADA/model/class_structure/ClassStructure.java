package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.metrics.class_metrics.ClassMetricValue;
import com.ucl.ADA.model.metrics.relation_metrics.RelationMetricValue;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.static_information.StaticInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ClassStructure extends BaseEntity {

    /* ************************************************************************
     *
     *  basic information
     *
     **************************************************************************/

    /**
     * qualified name of the class
     */
    private String className;

    /**
     * SHA1 file hash of this source file.
     */
    private String fileHash;

    /**
     * Fully qualified file name of this source file
     */
    private String fileName;

    /**
     * a set of source files that contains this class structure
     */
    private Set<Snapshot> snapshots = new HashSet<>();

    /* ************************************************************************
     *
     *  coupling information
     *
     **************************************************************************/

    /**
     * static information contains all declarations, outgoing dependence info and external invocation which is unchanged
     * when source file is not parsed
     */
    private StaticInfo staticInfo = new StaticInfo();

    /**
     * Information about the invocations of elements from this class by the other classes. String is the qualified name
     * of the class.
     */
    private Map<String, DependenceInfo> incomingDependenceInfos = new HashMap<>();

    /**
     * All of the metric values for the link between the current class and the linking classes.
     */
    private Map<String, RelationMetricValue> relationMetricValues = new HashMap<>();

    /**
     * The metrics corresponding to the current class
     */
    private ClassMetricValue classMetricValues = new ClassMetricValue();

    /* ************************************************************************
     *
     *  functions that update information of class structure
     *
     **************************************************************************/

//    /**
//     * Updates the package corresponding to the class.
//     *
//     * @param packageDeclaration the name of the package corresponding to the current class
//     */
//    public void setCurrentPackage(PackageDeclaration packageDeclaration) {
//        this.currentPackage = packageDeclaration;
//    }
//
//    /**
//     * Adds a Attribute Declaration to the ClassDependenceTree
//     *
//     * @param attributeDeclaration the attribute declaration object
//     */
//    public void addAttributeDeclaration(AttributeDeclaration attributeDeclaration) {
//        this.attributeDeclarations.add(attributeDeclaration);
//    }
//
//    /**
//     * Adds a Method Declaration to the ClassDependenceTree
//     *
//     * @param methodDeclaration the method declaration object
//     */
//    public void addMethodDeclaration(MethodDeclaration methodDeclaration) {
//        this.methodsDeclarations.add(methodDeclaration);
//    }
//
//    /**
//     * Adds a Constructor Declaration to the ClassDependenceTree
//     *
//     * @param constructorDeclaration the constructor declaration object
//     */
//    public void addConstructorDeclaration(ConstructorDeclaration constructorDeclaration) {
//        this.constructorDeclarations.add(constructorDeclaration);
//    }
//
//    /**
//     * Adds a new external package import
//     *
//     * @param packageInvocation an external package invocation object containing all of the related information about the package invocation being added
//     */
//    public void addExternalPackageImport(PackageInvocation packageInvocation) {
//        this.externalPackageImports.add(packageInvocation);
//    }
//
//    /**
//     * Adds a new external attribute invocation
//     *
//     * @param attributeInvocation an external attribute invocation object containing all of the related information about the attribute invocation being added
//     */
//    public void addExternalAttributeInvocation(AttributeInvocation attributeInvocation) {
//        this.externalAttributeInvocations.add(attributeInvocation);
//    }
//
//    /**
//     * Adds a new external constructor invocation
//     *
//     * @param constructorInvocation an external constructor invocation object containing all of the related information about the constructor invocation being added
//     */
//    public void addExternalConstructorInvocation(ConstructorInvocation constructorInvocation) {
//        this.externalConstructorInvocations.add(constructorInvocation);
//    }
//
//    /**
//     * Adds a new external method invocation
//     *
//     * @param methodInvocation an external method invocation object containing all of the related information about the method invocation being added
//     */
//    public void addExternalMethodInvocation(MethodInvocation methodInvocation) {
//        this.externalMethodInvocations.add(methodInvocation);
//    }
//
//    /**
//     * Adds a package invocation element as either an incoming or outgoing dependency, depending on the call.
//     *
//     * @param relatingClass     the class which the invocation relates to
//     * @param invocationType    either an incoming invocation type in the case that the package is being invoked from the
//     *                          relatingClass, or an outgoing invocation type in the case that the package is invoked from
//     *                          the relatingClass
//     * @param packageInvocation the package invocation object containing the data corresponding to the
//     *                          invocation in question
//     */
//    public void addPackageInvocationElement(String relatingClass, InvocationType invocationType, PackageInvocation packageInvocation) {
//        if (invocationType == InvocationType.OUTGOING) {
//            if (this.outgoingDependenceInfos.containsKey(relatingClass)) {
//                this.outgoingDependenceInfos.get(relatingClass).addNewPackage(packageInvocation);
//            } else {
//                DependenceInfo dependenceInfo = new DependenceInfo();
//                dependenceInfo.addNewPackage(packageInvocation);
//                this.outgoingDependenceInfos.put(relatingClass, dependenceInfo);
//            }
//        } else {
//            if (this.incomingDependenceInfos.containsKey(relatingClass)) {
//                this.incomingDependenceInfos.get(relatingClass).addNewPackage(packageInvocation);
//            } else {
//                DependenceInfo dependenceInfo = new DependenceInfo();
//                dependenceInfo.addNewPackage(packageInvocation);
//                this.incomingDependenceInfos.put(relatingClass, dependenceInfo);
//            }
//        }
//    }
//
//    /**
//     * Adds an attribute invocation element as either an incoming or outgoing dependency, depending on the call.
//     *
//     * @param relatingClass       the class which the invocation relates to
//     * @param invocationType      either an incoming invocation type in the case that the attribute is being invoked from the
//     *                            relatingClass, or an outgoing invocation type in the case that the attribute is invoked from
//     *                            the relatingClass
//     * @param attributeInvocation the attribute invocation object containing the data corresponding to the
//     *                            invocation in question
//     */
//    public void addAttributeInvocationElement(String relatingClass, InvocationType invocationType, AttributeInvocation attributeInvocation) {
//        if (invocationType == InvocationType.OUTGOING) {
//            if (this.outgoingDependenceInfos.containsKey(relatingClass)) {
//                this.outgoingDependenceInfos.get(relatingClass).addNewAttribute(attributeInvocation);
//            } else {
//                DependenceInfo dependenceInfo = new DependenceInfo();
//                dependenceInfo.addNewAttribute(attributeInvocation);
//                this.outgoingDependenceInfos.put(relatingClass, dependenceInfo);
//            }
//        } else {
//            if (this.incomingDependenceInfos.containsKey(relatingClass)) {
//                this.incomingDependenceInfos.get(relatingClass).addNewAttribute(attributeInvocation);
//            } else {
//                DependenceInfo dependenceInfo = new DependenceInfo();
//                dependenceInfo.addNewAttribute(attributeInvocation);
//                this.incomingDependenceInfos.put(relatingClass, dependenceInfo);
//            }
//        }
//    }
//
//    /**
//     * Adds a constructor invocation element as either an incoming or outgoing dependency, depending on the call.
//     *
//     * @param relatingClass         the class which the invocation relates to
//     * @param invocationType        either an incoming invocation type in the case that the constructor is being invoked from the
//     *                              relatingClass, or an outgoing invocation type in the case that the constructor is invoked from
//     *                              the relatingClass
//     * @param constructorInvocation the constructor invocation object containing the data corresponding to the
//     *                              invocation in question
//     */
//    public void addConstructorInvocationElement(String relatingClass, InvocationType invocationType, ConstructorInvocation constructorInvocation) {
//        if (invocationType == InvocationType.OUTGOING) {
//            if (this.outgoingDependenceInfos.containsKey(relatingClass)) {
//                this.outgoingDependenceInfos.get(relatingClass).addNewConstructor(constructorInvocation);
//            } else {
//                DependenceInfo dependenceInfo = new DependenceInfo();
//                dependenceInfo.addNewConstructor(constructorInvocation);
//                this.outgoingDependenceInfos.put(relatingClass, dependenceInfo);
//            }
//        } else {
//            if (this.incomingDependenceInfos.containsKey(relatingClass)) {
//                this.incomingDependenceInfos.get(relatingClass).addNewConstructor(constructorInvocation);
//            } else {
//                DependenceInfo dependenceInfo = new DependenceInfo();
//                dependenceInfo.addNewConstructor(constructorInvocation);
//                this.incomingDependenceInfos.put(relatingClass, dependenceInfo);
//            }
//        }
//    }
//
//    /**
//     * Adds a method invocation element as either an incoming or outgoing dependency, depending on the call.
//     *
//     * @param relatingClass    the class which the invocation relates to
//     * @param invocationType   either an incoming invocation type in the case that the method is being invoked from the
//     *                         relatingClass, or an outgoing invocation type in the case that the method is invoked from
//     *                         the relatingClass
//     * @param methodInvocation the method invocation object containing the data corresponding to the
//     *                         invocation in question
//     */
//    public void addMethodInvocationElement(String relatingClass, InvocationType invocationType, MethodInvocation methodInvocation) {
//        if (invocationType == InvocationType.OUTGOING) {
//            if (this.outgoingDependenceInfos.containsKey(relatingClass)) {
//                this.outgoingDependenceInfos.get(relatingClass).addNewMethod(methodInvocation);
//            } else {
//                DependenceInfo dependenceInfo = new DependenceInfo();
//                dependenceInfo.addNewMethod(methodInvocation);
//                this.outgoingDependenceInfos.put(relatingClass, dependenceInfo);
//            }
//        } else {
//            if (this.incomingDependenceInfos.containsKey(relatingClass)) {
//                this.incomingDependenceInfos.get(relatingClass).addNewMethod(methodInvocation);
//            } else {
//                DependenceInfo dependenceInfo = new DependenceInfo();
//                dependenceInfo.addNewMethod(methodInvocation);
//                this.incomingDependenceInfos.put(relatingClass, dependenceInfo);
//            }
//        }
//    }
//
//    public void computeClassMetric(ClassMetricType classMetricType) {
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
//    public void computeRelationMetric(RelationMetricType relationMetricType) {
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
//    public void computeAllClassMetrics() {
//        for (ClassMetricType classMetricType : ClassMetricType.values()) {
//            this.computeClassMetric(classMetricType);
//        }
//    }
//
//    /**
//     * compute relation metrics for all relation types
//     */
//    public void computeAllRelationMetrics() {
//        for (RelationMetricType relationMetricType : RelationMetricType.values()) {
//            this.computeRelationMetric(relationMetricType);
//        }
//    }
}
