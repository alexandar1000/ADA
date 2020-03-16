package com.ucl.ADA.model.metrics.relation_metrics;

import com.ucl.ADA.model.base_entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationMetricValue extends BaseEntity {

    /**
     * total number of incoming package imports from the other class
     */
    private Float numberOfPackageImportsIncoming = 0f;

    /**
     * total number of outgoing package imports to the other class
     */
    private Float numberOfPackageImportsOutgoing = 0f;

    /**
     * total number of incoming attribute invocations from the other class
     */
    private Float numberOfAttributeInvocationsIncoming = 0f;

    /**
     * total number of outgoing attribute invocations to the other class
     */
    private Float numberOfAttributeInvocationsOutgoing = 0f;


    /**
     * total number of incoming method invocations from the other class
     */
    private Float numberOfMethodInvocationsIncoming = 0f;

    /**
     * total number of outgoing method invocations to the other class
     */
    private Float numberOfMethodInvocationsOutgoing = 0f;


    /**
     * total number of incoming constructor imports from the other class
     */
    private Float numberOfConstructorInvocationsIncoming = 0f;

    /**
     * total number of outgoing constructor invocations to the other class
     */
    private Float numberOfConstructorInvocationsOutgoing = 0f;


    /**
     * sum of numbers of incoming and outgoing package imports between the two classes
     */
    private Float bidirectionalNumberOfPackageImports = 0f;

    /**
     * sum of numbers of incoming and outgoing attribute invocations between the two classes
     */
    private Float bidirectionalNumberOfAttributeInvocations = 0f;

    /**
     * sum of numbers of incoming and outgoing method invocations between the two classes
     */
    private Float bidirectionalNumberOfMethodInvocations = 0f;

    /**
     * sum of numbers of incoming and outgoing constructor invocations between the two classes
     */
    private Float bidirectionalNumberOfConstructorInvocations = 0f;
}
