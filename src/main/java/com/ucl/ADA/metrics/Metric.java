package com.ucl.ADA.metrics;

import javax.persistence.*;

@Entity
public class Metric {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetricTypes type;

    @Column(nullable = false)
    private Float value;

    public Metric() {
    }

    public Metric(MetricTypes type, Float value) {
        this.type = type;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetricTypes getType() {
        return type;
    }

    public void setType(MetricTypes type) {
        this.type = type;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
