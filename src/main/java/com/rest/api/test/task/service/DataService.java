package com.rest.api.test.task.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rest.api.test.task.dto.CitiesData;
import com.rest.api.test.task.exception.CityNotFoundException;
import com.rest.api.test.task.mapper.Mapper;
import com.rest.api.test.task.model.City;
import com.rest.api.test.task.model.Distance;
import com.rest.api.test.task.repository.CityRepository;
import com.rest.api.test.task.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;
    private final Mapper mapper;

    @Autowired
    public DataService(CityRepository cityRepository, DistanceRepository distanceRepository, Mapper mapper) {
        this.cityRepository = cityRepository;
        this.distanceRepository = distanceRepository;
        this.mapper = mapper;
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }


    public List<City> getCitiesByName(List<String> cities){
        List<City> result = new ArrayList<>();
        for (String s : cities) {
            if (cityRepository.findByName(s).isPresent()) {
                result.add(cityRepository.findByName(s).get());
            }else {
                throw new CityNotFoundException(String.format("City with name %s not found.",s));
            }
        }
        return result;
    }



     public void saveData(MultipartFile file){

         try (InputStream inputStream = file.getInputStream()) {
             XmlMapper xmlMapper = new XmlMapper();
            CitiesData data = xmlMapper.readValue(inputStream, CitiesData.class);


            List<City> cities = data.getCities().stream().map(mapper::cityDTOToCity).toList();
            List<City> newCities = new ArrayList<>();



            for (City city : cities) {
                 boolean existsInDatabase = getAllCities().stream()
                         .anyMatch(x -> x.getName().equals(city.getName()));

                 if (!existsInDatabase) {
                     newCities.add(city);
                 }
             }


            cityRepository.saveAll(newCities);



             if(data.getDistances()!=null) {
                 List<Distance> distances = data.getDistances().stream().map(mapper::distanceDTOToDistance).toList();
                 List<Distance> newDistances = new ArrayList<>();

                 for (Distance distance : distances) {
                     boolean existsFromCity = distanceRepository.findAll().stream()
                             .anyMatch(x -> x.getFromCity().equals(distance.getFromCity()));

                     boolean existsToCity = distanceRepository.findAll().stream()
                             .anyMatch(x -> x.getToCity().equals(distance.getToCity()));

                     if (!(existsToCity&&existsFromCity)) {
                         newDistances.add(distance);
                     }
                 }

                 distanceRepository.saveAll(newDistances);
             }






         } catch (Exception e) {
             e.printStackTrace();
         }
     }

}
