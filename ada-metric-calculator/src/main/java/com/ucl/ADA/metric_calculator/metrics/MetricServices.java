package com.ucl.ADA.metric_calculator.metrics;

import com.ucl.ADA.metric_calculator.metrics_structure.ProjectMetricsContainer;
import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServices {
    @Autowired
    private MetricRepository metricRepository;

    protected void saveMetric(MetricTypes metricType, float value) {
        Metric m = new Metric(metricType, value);
        metricRepository.save(m);
    }

    public ProjectMetricsContainer computeAllMetrics(ProjectDependenceTree projectDependenceTree) {
//
//        // TODO: Implement proper metrics calculation
//        ProjectMetricsContainer projectMetricsContainer = new ProjectMetricsContainer(projectDependenceTree.getClassNames());
//
//        for (String className : projectDependenceTree.getClassNames()) {
//            projectMetricsContainer.getClassMetrics().put(className, new ClassMetricsContainer());
//        }

        return null;
    }


    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public Metric getMetric(Long id) {
        return metricRepository.findById(id).orElse(null);
    }
}

