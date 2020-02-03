package com.ucl.ADA.metric_calculator.metrics;

import com.github.javafaker.Faker;
import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServices {
    @Autowired
    private MetricRepository metricRepository;

    private Faker faker = new Faker();

    protected void saveRandomMetric() {
        Metric m = new Metric(MetricTypes.SIMPLE_METRIC, (float) faker.number().randomDouble(6, -100000000, 10000000));
        metricRepository.save(m);
    }

    protected void saveMetric(MetricTypes metricType, float value) {
        Metric m = new Metric(metricType, value);
        metricRepository.save(m);
    }

    protected void saveMetric(Metric m) {
        metricRepository.save(m);
    }

    public void computeAllMetrics() {
        // Simple Metric
        computeSimpleMetric();

        // Medium Metric
        computeMediumMetric();

        // Complex Metric
        computeComplexMetric();
    }

    public void computeAllMetrics(ProjectDependenceTree data) {
        // Simple Metric
        computeSimpleMetric();

        // Medium Metric
        computeMediumMetric();

        // Complex Metric
        computeComplexMetric();
    }

    private void computeSimpleMetric() {
        saveMetric(MetricTypes.SIMPLE_METRIC, (float) faker.number().randomDouble(6, 0, 1));
    }
    private void computeMediumMetric() {
        saveMetric(MetricTypes.MEDIUM_METRIC, (float) faker.number().randomDouble(6, 0, 1));
    }
    private void computeComplexMetric() {
        saveMetric(MetricTypes.COMPLEX_METRIC, (float) faker.number().randomDouble(6, 0, 1));
    }

    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public Metric getMetric(Long id) {
        return metricRepository.findById(id).orElse(null);
    }
}

