package com.rest.api.test.task.controller;


import com.rest.api.test.task.dto.CityDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(String calculationType, List<CityDTO> fromCity, List<CityDTO> toCity){


        return ResponseEntity.ok(HttpStatus.OK);
    }
}
