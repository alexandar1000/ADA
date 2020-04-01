package com.ucl.ADA.model.metrics.class_metrics;

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
@Table(name = "CLASS_METRIC_VALUE")
public class ClassMetricValue extends BaseEntity {

    /**
     * total number of incoming package imports
     */
    @Column(name = "number_of_package_imports_incoming", nullable = false)
    private Float numberOfPackageImportsIncoming = 0f;

    /**
     * total number of outgoing package imports
     */
    @Column(name = "number_of_package_imports_outgoing", nullable = false)
    private Float numberOfPackageImportsOutgoing = 0f;

    /**
     * total number of incoming attribute invocations
     */
    @Column(name = "number_of_attribute_invocation_incoming", nullable = false)
    private Float numberOfAttributeInvocationsIncoming = 0f;

    /**
     * total number of outgoing attribute invocations
     */
    @Column(name = "number_of_attribute_invocation_outgoing", nullable = false)
    private Float numberOfAttributeInvocationsOutgoing = 0f;


    /**
     * total number of incoming method invocations
     */
    @Column(name = "number_of_method_invocation_incoming", nullable = false)
    private Float numberOfMethodInvocationsIncoming = 0f;

    /**
     * total number of outgoing method invocations
     */
    @Column(name = "number_of_method_invocation_outgoing", nullable = false)
    private Float numberOfMethodInvocationsOutgoing = 0f;


    /**
     * total number of incoming constructor imports
     */
    @Column(name = "number_of_constructor_invocation_incoming", nullable = false)
    private Float numberOfConstructorInvocationsIncoming = 0f;

    /**
     * total number of outgoing constructor invocations
     */
    @Column(name = "number_of_constructor_invocation_outgoing", nullable = false)
    private Float numberOfConstructorInvocationsOutgoing = 0f;


    /**
     * sum of numbers of incoming and outgoing package imports
     */
    @Column(name = "bidirectional_number_of_package_imports", nullable = false)
    private Float bidirectionalNumberOfPackageImports = 0f;

    /**
     * sum of numbers of incoming and outgoing attribute invocations
     */
    @Column(name = "bidirectional_number_of_attribute_invocations", nullable = false)
    private Float bidirectionalNumberOfAttributeInvocations = 0f;

    /**
     * sum of numbers of incoming and outgoing method invocations
     */
    @Column(name = "bidirectional_number_of_method_invocations", nullable = false)
    private Float bidirectionalNumberOfMethodInvocations = 0f;

    /**
     * sum of numbers of incoming and outgoing constructor invocations
     */
    @Column(name = "bidirectional_number_of_constructor_invocations", nullable = false)
    private Float bidirectionalNumberOfConstructorInvocations = 0f;

}
