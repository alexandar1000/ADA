package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassMetricsContainer {

    private HashMap<String, MetricValue> metricValues = new HashMap<>();

}
