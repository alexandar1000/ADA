package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassMetrics {

    private HashMap<String, MetricValues> metricValues = new HashMap<>();

}
