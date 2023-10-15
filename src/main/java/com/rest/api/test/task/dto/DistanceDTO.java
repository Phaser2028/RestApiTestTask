package com.rest.api.test.task.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;



@JacksonXmlRootElement(localName = "Distance")
public class DistanceDTO {
    @JacksonXmlProperty(localName = "fromCity")
    private String fromCity;
    @JacksonXmlProperty(localName = "toCity")
    private String toCity;
    @JacksonXmlProperty(localName = "distance")
    private Double distance;

    public DistanceDTO() {
    }

    public DistanceDTO(String fromCity, String toCity, Double distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
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
