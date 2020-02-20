package com.ucl.ADA.model.metrics;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ClassMetricsContainer {

    /**
     * Computes all of the metrics for a class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     *//*
    protected void computeAllClassMetrics(String correspondingClass, ProjectStructure projectStructure) {
        this.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, projectStructure);

        this.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, projectStructure);

        this.calculateNumberOfClassPackageImportsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, projectStructure);
    }

    *//**
     * Computes all of the metrics for the relation between the current class and all of the relating classes..
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     *//*
    protected void computeAllRelationMetrics(String correspondingClass, ProjectStructure projectStructure) {
        this.calculateBidirectionalNumberOfRelationPackageImports(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfRelationAttributeInvocations(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfRelationConstructorInvocations(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfRelationMethodInvocations(correspondingClass, projectStructure);

        this.calculateNumberOfRelationPackageImportsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfRelationAttributeInvocationsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfRelationConstructorInvocationsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfRelationMethodInvocationsOutgoing(correspondingClass, projectStructure);

        this.calculateNumberOfRelationPackageImportsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfRelationAttributeInvocationsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfRelationConstructorInvocationsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfRelationMethodInvocationsIncoming(correspondingClass, projectStructure);
    }*/
}
