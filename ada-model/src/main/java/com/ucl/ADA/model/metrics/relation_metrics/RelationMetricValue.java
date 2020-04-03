package com.ucl.ADA.model.metrics.relation_metrics;

import com.ucl.ADA.model.base_entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RELATION_METRIC_VALUE")
public class RelationMetricValue extends BaseEntity {

    /**
     * total number of incoming attribute invocations from the other class
     */
    @Column(name = "number_of_attribute_invocation_incoming", nullable = false)
    private Float numberOfAttributeInvocationsIncoming = 0f;

    /**
     * total number of outgoing attribute invocations to the other class
     */
    @Column(name = "number_of_attribute_invocation_outgoing", nullable = false)
    private Float numberOfAttributeInvocationsOutgoing = 0f;


    /**
     * total number of incoming method invocations from the other class
     */
    @Column(name = "number_of_method_invocation_incoming", nullable = false)
    private Float numberOfMethodInvocationsIncoming = 0f;

    /**
     * total number of outgoing method invocations to the other class
     */
    @Column(name = "number_of_method_invocation_outgoing", nullable = false)
    private Float numberOfMethodInvocationsOutgoing = 0f;


    /**
     * total number of incoming constructor imports from the other class
     */
    @Column(name = "number_of_constructor_invocation_incoming", nullable = false)
    private Float numberOfConstructorInvocationsIncoming = 0f;

    /**
     * total number of outgoing constructor invocations to the other class
     */
    @Column(name = "number_of_constructor_invocation_outgoing", nullable = false)
    private Float numberOfConstructorInvocationsOutgoing = 0f;


    /**
     * sum of numbers of incoming and outgoing attribute invocations between the two classes
     */
    @Column(name = "bidirectional_number_of_attribute_invocations", nullable = false)
    private Float bidirectionalNumberOfAttributeInvocations = 0f;

    /**
     * sum of numbers of incoming and outgoing method invocations between the two classes
     */
    @Column(name = "bidirectional_number_of_method_invocations", nullable = false)
    private Float bidirectionalNumberOfMethodInvocations = 0f;

    /**
     * sum of numbers of incoming and outgoing constructor invocations between the two classes
     */
    @Column(name = "bidirectional_number_of_constructor_invocations", nullable = false)
    private Float bidirectionalNumberOfConstructorInvocations = 0f;
}
