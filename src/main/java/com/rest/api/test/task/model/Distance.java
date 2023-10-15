package com.rest.api.test.task.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "Distance")
public class Distance{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @JoinColumn(name = "from_city",referencedColumnName="name")
    private String fromCity;
    @JoinColumn(name = "to_city",referencedColumnName="name")
    private String toCity;
    @Column(name = "distance")
    private Double distance;

    public Distance() {
    }

    public Distance(Long id, String fromCity, String toCity, Double distance) {
        this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}