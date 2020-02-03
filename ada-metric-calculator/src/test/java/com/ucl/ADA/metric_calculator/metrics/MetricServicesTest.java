package com.ucl.ADA.metric_calculator.metrics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MetricServicesTest {

    @InjectMocks
    private MetricServices metricServices;

    @Mock
    private MetricRepository metricRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test the retrieval of all of the metrics.
     */
    @Test
    void getAllMetrics() {
        Metric m1 = new Metric(MetricTypes.SIMPLE_METRIC, 1.0123F);
        Metric m2 = new Metric(MetricTypes.MEDIUM_METRIC, 2.4567F);
        Metric m3 = new Metric(MetricTypes.COMPLEX_METRIC, 3.8910F);

        m1.setId(1L);
        m2.setId(2L);
        m3.setId(3L);

        List<Metric> metricArrayList = new ArrayList<>();
        metricArrayList.add(m1);
        metricArrayList.add(m2);
        metricArrayList.add(m3);

        when(metricRepository.findAll()).thenReturn(metricArrayList);

        List<Metric> retrievedMetrics = metricServices.getAllMetrics();

        verify(metricRepository).findAll();

        assertThat(retrievedMetrics).hasSize(3);

        assertThat(retrievedMetrics.get(0).getId()).isEqualTo(1L);
        assertThat(retrievedMetrics.get(0).getType()).isEqualTo(MetricTypes.SIMPLE_METRIC);
        assertThat(retrievedMetrics.get(0).getValue()).isEqualTo(1.0123F);

        assertThat(retrievedMetrics.get(1).getId()).isEqualTo(2L);
        assertThat(retrievedMetrics.get(1).getType()).isEqualTo(MetricTypes.MEDIUM_METRIC);
        assertThat(retrievedMetrics.get(1).getValue()).isEqualTo(2.4567F);

        assertThat(retrievedMetrics.get(2).getId()).isEqualTo(3L);
        assertThat(retrievedMetrics.get(2).getType()).isEqualTo(MetricTypes.COMPLEX_METRIC);
        assertThat(retrievedMetrics.get(2).getValue()).isEqualTo(3.8910F);
    }

    /**
     * Test the retrieval of all of the present metrics when the source is empty.
     */
    @Test
    void getAllMetricsFromEmptySource() {
        List<Metric> metricArrayList = new ArrayList<>();
        when(metricRepository.findAll()).thenReturn(metricArrayList);

        List<Metric> retrievedMetrics = metricServices.getAllMetrics();

        verify(metricRepository).findAll();

        assertThat(retrievedMetrics).isNotNull();
        assertThat(retrievedMetrics).hasSize(0);
    }

    /**
     * Test the retrieval of a specific metric (with a specific ID).
     */
    @Test
    void getMetric() {
        Metric m = new Metric(MetricTypes.SIMPLE_METRIC, 8.07F);
        m.setId(1L);
        when(metricRepository.findById(1L)).thenReturn(java.util.Optional.of(m));
        Metric metric = metricServices.getMetric(1L);

        verify(metricRepository).findById(1L);

        assertThat(metric.getId()).isEqualTo(1L);
        assertThat(metric.getType()).isEqualTo(MetricTypes.SIMPLE_METRIC);
        assertThat(metric.getValue()).isEqualTo(8.07F);

    }

    /**
     * Test the retrieval of a non-existing specific metric (with a specific ID).
     */
    @Test
    void getMetricNotFound() {
        when(metricRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Metric metric = metricServices.getMetric(1L);

        verify(metricRepository).findById(1L);

        assertThat(metric).isNull();
    }
}