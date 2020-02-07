package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MetricValue {

    private Double simpleMetric;
    private Double mediumMetric;
    private Double complexMetric;
}
