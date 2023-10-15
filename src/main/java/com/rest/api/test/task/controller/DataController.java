package com.rest.api.test.task.controller;


import com.rest.api.test.task.dto.CitySummary;
import com.rest.api.test.task.model.City;
import com.rest.api.test.task.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadData(MultipartFile file){
        dataService.saveData(file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getCities")
    public ResponseEntity<?> getCities(){
        List<City> cities = dataService.getAllCities();

        List<CitySummary> citySummaries = cities.stream()
                .map(city -> new CitySummary(city.getId(), city.getName())).toList();

        return ResponseEntity.ok(citySummaries);


    }





}
