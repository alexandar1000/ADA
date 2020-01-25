package com.ucl.ADA.metrics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MetricControllerTest {

    @InjectMocks
    private MetricController metricController;

    @Mock
    private MetricServices metricService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test that the controller gets the element with the user id
     */
    @Test
    void testGetUserById() {
        Metric m = new Metric(MetricTypes.SIMPLE_METRIC, 8.07F);
        m.setId(1L);
        when(metricService.getMetric(1L)).thenReturn(m);
        Metric metric = metricController.retrieveMetricById(1L);
        verify(metricService).getMetric(1L);
        assertEquals(1L, metric.getId().longValue());
    }

    /**
     * Test that the element is not found and null is returned
     */
    @Test
    void testGetUserByIdNotFound() {
        Metric metric = metricController.retrieveMetricById(1L);
        verify(metricService).getMetric(1L);
        assertNull(metric);
    }
}
