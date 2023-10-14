package com.rest.api.test.task.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rest.api.test.task.dto.CitiesData;
import com.rest.api.test.task.dto.CityDTO;
import com.rest.api.test.task.dto.DistanceDTO;
import com.rest.api.test.task.mapper.Mapper;
import com.rest.api.test.task.model.City;
import com.rest.api.test.task.model.Distance;
import com.rest.api.test.task.repository.CityRepository;
import com.rest.api.test.task.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;
    private final Mapper mapper;

    @Autowired
    public CityService(CityRepository cityRepository, DistanceRepository distanceRepository, Mapper mapper) {
        this.cityRepository = cityRepository;
        this.distanceRepository = distanceRepository;
        this.mapper = mapper;
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public void saveData(MultipartFile file){
        try (InputStream inputStream = file.getInputStream()) {
            XmlMapper xmlMapper = new XmlMapper();
            CitiesData data = xmlMapper.readValue(inputStream, CitiesData.class);

            List<City> cities = data.getCities().stream().map(mapper::cityDTOToCity).toList();
            List<Distance> distances = data.getDistances().stream().map(mapper::distanceDTOToDistance).toList();

            System.out.println(data.getDistances());
            System.out.println(distances);

            cityRepository.saveAll(cities);
            distanceRepository.saveAll(distances);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
