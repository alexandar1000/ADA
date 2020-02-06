package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MetricValue {
    private Float numberOfPackageImportsIncoming;
    private Float numberOfPackageImportsOutgoing;

    private Float numberOfAttributeInvocationsIncoming;
    private Float numberOfAttributeInvocationsOutgoing;

    private Float numberOfMethodInvocationsIncoming;
    private Float numberOfMethodInvocationsOutgoing;

    private Float numberOfConstructorInvocationsIncoming;
    private Float numberOfConstructorInvocationsOutgoing;

    private Float bidirectionalNumberOfPackageImports;

    private Float bidirectionalNumberOfAttributeInvocations;

    private Float bidirectionalNumberOfMethodInvocations;

    private Float bidirectionalNumberOfConstructorInvocations;
}
