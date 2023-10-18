package com.rest.api.test.task.controller;


import com.rest.api.test.task.dto.DistanceDTO;
import com.rest.api.test.task.exception.CityNotFoundException;
import com.rest.api.test.task.mapper.Mapper;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CalculatorController {
    private final CalculateService calculateService;
    private final Mapper mapper;

    @Autowired
    public CalculatorController(CalculateService calculateService, Mapper mapper) {
        this.calculateService = calculateService;
        this.mapper = mapper;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>>calculate(@RequestBody DistanceRequest distanceRequest) {

        Map<String, Object> result = new HashMap<>();
        List<DistanceDTO> resultsDistanceMatrix;
        List<DistanceDTO> resultsCrowFlight;


        try {
            if (distanceRequest.getCalculationType().equals("Crowflight")) {

                resultsCrowFlight = calculateService.calculateCrowFlightDistances(distanceRequest).stream()
                        .map(mapper::distanceToDistanceDTO).collect(Collectors.toList());
                result.put("Crowflight", resultsCrowFlight);

                return ResponseEntity.ok(result);

            } else if (distanceRequest.getCalculationType().equals("Distance matrix")) {

                resultsDistanceMatrix = calculateService.calculateMatrixDistances(distanceRequest).stream()
                        .map(mapper::distanceToDistanceDTO).collect(Collectors.toList());
                result.put("Distance matrix", resultsDistanceMatrix);

                return ResponseEntity.ok(result);

            } else if (distanceRequest.getCalculationType().equals("All")) {

                resultsDistanceMatrix = calculateService.calculateMatrixDistances(distanceRequest).stream()
                        .map(mapper::distanceToDistanceDTO).collect(Collectors.toList());
                resultsCrowFlight = calculateService.calculateCrowFlightDistances(distanceRequest).stream()
                        .map(mapper::distanceToDistanceDTO).collect(Collectors.toList());

                result.put("Crowflight", resultsCrowFlight);
                result.put("Distance matrix", resultsDistanceMatrix);


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

