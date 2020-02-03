package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Getter @Setter @NoArgsConstructor
public class ClassMetrics {
    private HashMap<String, MetricValues> invokesInfo = new HashMap<>();
    private HashMap<String, MetricValues> invokedInfo = new HashMap<>();

    public ClassMetrics randomParsedDataOfClassPlaceholder(String currentClass, ArrayList<String> classNames) {
        Random random = new Random();
        for (String className : classNames) {
            if (!currentClass.equals(className) && random.nextBoolean()) {
                invokesInfo.put(className, new MetricValues().assignRandomValues());
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return "\n\t\tParsedDataOfClassPlaceholder{" +
                "\n\t\tinvokesInfo=" + invokesInfo +
                ", \n\t\tinvokedInfo=" + invokedInfo +
                "\n\t\t}";
    }
}
