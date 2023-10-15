package com.rest.api.test.task.model;

import java.util.List;

public class DistanceRequest {
    private String calculationType;
    private List<String> fromCities;
    private List<String> toCities;

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public List<String> getFromCities() {
        return fromCities;
    }

    public void setFromCities(List<String> fromCities) {
        this.fromCities = fromCities;
    }

    public List<String> getToCities() {
        return toCities;
    }

    public void setToCities(List<String> toCities) {
        this.toCities = toCities;
    }
}
