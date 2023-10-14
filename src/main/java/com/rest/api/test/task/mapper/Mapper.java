package com.rest.api.test.task.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.test.task.dto.CityDTO;
import com.rest.api.test.task.dto.DistanceDTO;
import com.rest.api.test.task.model.City;
import com.rest.api.test.task.model.Distance;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private ObjectMapper objectMapper = new ObjectMapper();

    public  CityDTO cityToCityDTO(City city) {
        return objectMapper.convertValue(city, CityDTO.class);
    }

    public City cityDTOToCity(CityDTO cityDTO) {
        return objectMapper.convertValue(cityDTO, City.class);
    }

    public DistanceDTO distanceToDistanceDTO(Distance distance) {
        return objectMapper.convertValue(distance, DistanceDTO.class);
    }

    public Distance distanceDTOToDistance(DistanceDTO distanceDTO) {
        return objectMapper.convertValue(distanceDTO, Distance.class);
    }

}
