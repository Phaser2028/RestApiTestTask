package com.rest.api.test.task.controller;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rest.api.test.task.dto.CitiesData;
import com.rest.api.test.task.dto.CityDTO;
import com.rest.api.test.task.dto.DistanceDTO;
import com.rest.api.test.task.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/data")
public class DataController {

    private final CityService cityService;

    @Autowired
    public DataController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadXmlData(MultipartFile file) throws IOException, JAXBException {
        try (InputStream inputStream = file.getInputStream()) {
            XmlMapper xmlMapper = new XmlMapper();
            CitiesData data = xmlMapper.readValue(inputStream, CitiesData.class);

            List<CityDTO> cities = data.getCities();
            List<DistanceDTO> distances = data.getDistances();

            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getCities")
    public ResponseEntity<HttpStatus> getCities(){

        return ResponseEntity.ok(HttpStatus.OK);
    }





}
