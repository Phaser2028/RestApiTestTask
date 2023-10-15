package com.rest.api.test.task.controller;


import com.rest.api.test.task.dto.CityDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(String calculationType, @RequestBody List<CityDTO> fromCity,@RequestBody List<CityDTO> toCity){
        if(calculationType.equals("Crowflight")){
            
        } else if (calculationType.equals("Distance Matrix")) {
            
        } else if (calculationType.equals("All")) {

        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
