package com.rest.api.test.task.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "Data")
public class CitiesData {
    @JacksonXmlProperty(localName = "Cities")
    private List<CityDTO> cities;

    @JacksonXmlProperty(localName = "Distances")
    private List<DistanceDTO> distances;

    public CitiesData() {
    }

    public CitiesData(List<CityDTO> cities, List<DistanceDTO> distances) {
        this.cities = cities;
        this.distances = distances;
    }

    public List<CityDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityDTO> cities) {
        this.cities = cities;
    }

    public List<DistanceDTO> getDistances() {
        return distances;
    }

    public void setDistances(List<DistanceDTO> distances) {
        this.distances = distances;
    }
}