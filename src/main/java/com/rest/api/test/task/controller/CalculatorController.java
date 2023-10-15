package com.rest.api.test.task.controller;


import com.rest.api.test.task.exception.CityNotFoundException;
import com.rest.api.test.task.model.DistanceRequest;
import com.rest.api.test.task.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class CalculatorController {
    private final CalculateService calculateService;

    @Autowired
    public CalculatorController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody DistanceRequest distanceRequest) {

        try {
            if (distanceRequest.getCalculationType().equals("Crowflight")) {
                return ResponseEntity.ok(calculateService.calculateCrowFlightDistances(distanceRequest));
            } else if (distanceRequest.getCalculationType().equals("Distance matrix")) {
                return ResponseEntity.ok(Arrays.asList(calculateService.calculateMatrixDistances(distanceRequest)));
            }
        } catch (CityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.badRequest().body("Bad request");
    }
}
