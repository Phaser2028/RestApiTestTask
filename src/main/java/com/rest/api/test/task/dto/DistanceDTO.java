package com.rest.api.test.task.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;



@JacksonXmlRootElement(localName = "Distance")
public class DistanceDTO {

    @JacksonXmlProperty(localName = "fromCity")
    private CityDTO fromCity;
    @JacksonXmlProperty(localName = "toCity")
    private CityDTO toCity;
    @JacksonXmlProperty(localName = "distance")
    private Double distance;

    public DistanceDTO() {
    }

    public DistanceDTO(CityDTO fromCity, CityDTO toCity, Double distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public CityDTO getFromCity() {
        return fromCity;
    }

    public void setFromCity(CityDTO fromCity) {
        this.fromCity = fromCity;
    }

    public CityDTO getToCity() {
        return toCity;
    }

    public void setToCity(CityDTO toCity) {
        this.toCity = toCity;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "DistanceDTO{" +
                "fromCity=" + fromCity.getName() +
                ", toCity=" + toCity.getName() +
                '}';
    }
}
