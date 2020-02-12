package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ClassMetricValue {
    private Float numberOfPackageImportsIncoming = 0f;
    private Float numberOfPackageImportsOutgoing = 0f;

    private Float numberOfAttributeInvocationsIncoming = 0f;
    private Float numberOfAttributeInvocationsOutgoing = 0f;

    private Float numberOfMethodInvocationsIncoming = 0f;
    private Float numberOfMethodInvocationsOutgoing = 0f;

    private Float numberOfConstructorInvocationsIncoming = 0f;
    private Float numberOfConstructorInvocationsOutgoing = 0f;

    private Float bidirectionalNumberOfPackageImports = 0f;

    private Float bidirectionalNumberOfAttributeInvocations = 0f;

    private Float bidirectionalNumberOfMethodInvocations = 0f;

    private Float bidirectionalNumberOfConstructorInvocations = 0f;
}
