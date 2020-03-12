package com.ucl.ADA.model.metrics.class_metrics;

import com.ucl.ADA.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassMetricValue extends BaseEntity {

    /**
     * total number of incoming package imports
     */
    private Float numberOfPackageImportsIncoming = 0f;

    /**
     * total number of outgoing package imports
     */
    private Float numberOfPackageImportsOutgoing = 0f;

    /**
     * total number of incoming attribute invocations
     */
    private Float numberOfAttributeInvocationsIncoming = 0f;

    /**
     * total number of outgoing attribute invocations
     */
    private Float numberOfAttributeInvocationsOutgoing = 0f;


    /**
     * total number of incoming method invocations
     */
    private Float numberOfMethodInvocationsIncoming = 0f;

    /**
     * total number of outgoing method invocations
     */
    private Float numberOfMethodInvocationsOutgoing = 0f;


    /**
     * total number of incoming constructor imports
     */
    private Float numberOfConstructorInvocationsIncoming = 0f;

    /**
     * total number of outgoing constructor invocations
     */
    private Float numberOfConstructorInvocationsOutgoing = 0f;


    /**
     * sum of numbers of incoming and outgoing package imports
     */
    private Float bidirectionalNumberOfPackageImports = 0f;

    /**
     * sum of numbers of incoming and outgoing attribute invocations
     */
    private Float bidirectionalNumberOfAttributeInvocations = 0f;

    /**
     * sum of numbers of incoming and outgoing method invocations
     */
    private Float bidirectionalNumberOfMethodInvocations = 0f;

    /**
     * sum of numbers of incoming and outgoing constructor invocations
     */
    private Float bidirectionalNumberOfConstructorInvocations = 0f;

}
