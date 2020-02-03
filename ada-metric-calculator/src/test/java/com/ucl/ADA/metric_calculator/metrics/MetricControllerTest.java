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

public class MetricControllerTest {

    @InjectMocks
    private MetricController metricController;

    @Mock
    private MetricServices metricService;


    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test that the controller gets the element with the user id.
     */
    @Test
    void testGetUserById() {
        Metric m = new Metric(MetricTypes.SIMPLE_METRIC, 8.07F);
        m.setId(1L);
        when(metricService.getMetric(1L)).thenReturn(m);
        Metric metric = metricController.retrieveMetricById(1L);

        verify(metricService).getMetric(1L);

        assertThat(metric.getId()).isEqualTo(1L);
        assertThat(metric.getType()).isEqualTo(MetricTypes.SIMPLE_METRIC);
        assertThat(metric.getValue()).isEqualTo(8.07F);

    }

    /**
     * Test that the element is not found and null is returned.
     */
    @Test
    void testGetUserByIdNotFound() {
        Metric metric = metricController.retrieveMetricById(1L);
        verify(metricService).getMetric(1L);
        assertThat(metric).isNull();
    }

    /**
     * Test that the retrieval of the elements works when they exist inside an object.
     */
    @Test
    void testRetrieveAllMetrics() {
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

        when(metricService.getAllMetrics()).thenReturn(metricArrayList);

        List<Metric> retrievedMetrics = metricController.retrieveAllMetrics();

        verify(metricService).getAllMetrics();

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
     * Test that the retrieval of elements works if the source is empty.
     */
    @Test
    void testRetrieveAllMetricsFromEmptySource() {
        List<Metric> metricArrayList = new ArrayList<>();
        when(metricService.getAllMetrics()).thenReturn(metricArrayList);

        List<Metric> retrievedMetrics = metricController.retrieveAllMetrics();

        verify(metricService).getAllMetrics();

        assertThat(retrievedMetrics).isNotNull();
        assertThat(retrievedMetrics).hasSize(0);
    }
}
