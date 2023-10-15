package com.rest.api.test.task.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rest.api.test.task.dto.CitiesData;
import com.rest.api.test.task.mapper.Mapper;
import com.rest.api.test.task.model.City;
import com.rest.api.test.task.model.Distance;
import com.rest.api.test.task.repository.CityRepository;
import com.rest.api.test.task.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;
    private final Mapper mapper;

    private CitiesData data;

    private List<City> cities;

    private List<Distance> distances;


    @Autowired
    public CityService(CityRepository cityRepository, DistanceRepository distanceRepository, Mapper mapper) {
        this.cityRepository = cityRepository;
        this.distanceRepository = distanceRepository;
        this.mapper = mapper;
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }




     public void parseData(MultipartFile file){

         try (InputStream inputStream = file.getInputStream()) {
             XmlMapper xmlMapper = new XmlMapper();
             this.data = xmlMapper.readValue(inputStream, CitiesData.class);

             if(data.getCities()!=null) {
                 this.cities = data.getCities().stream().map(mapper::cityDTOToCity).toList();
                 saveCities();
             }

             if(data.getDistances()!=null){
                 this.distances = data.getDistances().stream().map(mapper::distanceDTOToDistance).toList();



                 saveDistances();
             }

         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveDistances(){
        distanceRepository.saveAll(this.distances);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveCities(){
        cityRepository.saveAll(this.cities);
    }

}
