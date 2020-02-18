package com.ucl.ADA.metric_calculator.metrics;

import com.ucl.ADA.metric_calculator.metrics_structure.ClassMetricTypes;
import com.ucl.ADA.metric_calculator.metrics_structure.ProjectMetricsContainer;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServices {
    @Autowired
    private MetricRepository metricRepository;

    protected void saveMetric(ClassMetricTypes metricType, float value) {
        Metric m = new Metric(metricType, value);
        metricRepository.save(m);
    }

    public ProjectMetricsContainer computeAllMetrics(ProjectStructure projectStructure) {
        ProjectMetricsContainer projectMetricsContainer = new ProjectMetricsContainer();
        projectMetricsContainer.computeAllMetrics(projectStructure);
        return projectMetricsContainer;
    }


    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public Metric getMetric(Long id) {
        return metricRepository.findById(id).orElse(null);
    }
}

