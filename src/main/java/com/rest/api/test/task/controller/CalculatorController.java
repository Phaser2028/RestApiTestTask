package com.rest.api.test.task.controller;


import com.rest.api.test.task.exception.CityNotFoundException;
import com.rest.api.test.task.model.DistanceRequest;
import com.rest.api.test.task.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {
    private final CalculateService calculateService;

    @Autowired
    public CalculatorController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>>calculate(@RequestBody DistanceRequest distanceRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (distanceRequest.getCalculationType().equals("Crowflight")) {
                result.put("Crowflight", calculateService.calculateCrowFlightDistances(distanceRequest));
                return ResponseEntity.ok(result);
            } else if (distanceRequest.getCalculationType().equals("Distance matrix")) {
                result.put("Distance matrix", calculateService.calculateMatrixDistances(distanceRequest));
                return ResponseEntity.ok(result);
            } else if (distanceRequest.getCalculationType().equals("All")) {
                result.put("Crowflight", calculateService.calculateCrowFlightDistances(distanceRequest));
                result.put("Distance matrix", calculateService.calculateMatrixDistances(distanceRequest));
                return ResponseEntity.ok(result);
            }else {
                result.put("Error", "Invalid calculation type");
                return ResponseEntity.badRequest().body(result);
            }
        } catch (CityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, City not found, e);
        }
    }

}

