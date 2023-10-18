package com.rest.api.test.task.dto;

public class DistanceDTO {

    private CityDTO fromCity;

    private CityDTO  toCity;

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
}
