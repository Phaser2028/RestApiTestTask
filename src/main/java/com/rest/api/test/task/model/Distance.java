package com.rest.api.test.task.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Distance")
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_city",referencedColumnName="name")
    private City fromCity;
    @ManyToOne
    @JoinColumn(name = "to_city",referencedColumnName="name")
    private City toCity;
    @Column
    private Float distance;

    public Distance() {
    }

    public Distance(Long id, City fromCity, City toCity, Float distance) {
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

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}