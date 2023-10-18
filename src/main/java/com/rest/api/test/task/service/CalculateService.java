package com.rest.api.test.task.service;

import com.rest.api.test.task.exception.CityNotFoundException;
import com.rest.api.test.task.model.City;
import com.rest.api.test.task.model.Distance;
import com.rest.api.test.task.model.DistanceRequest;
import com.rest.api.test.task.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateService {

    @Autowired
    private DataService dataService;

    @Autowired
    private DistanceRepository distanceRepository;

    public List<Distance> calculateCrowFlightDistances(DistanceRequest distanceRequest) throws CityNotFoundException {
        List<Distance> crowFlightDistances = new ArrayList<>();
        List<City> fromCities = dataService.getCitiesByName(distanceRequest.getFromCities());
        List<City> toCities = dataService.getCitiesByName(distanceRequest.getToCities());

        for (City fromCity : fromCities) {

            Distance distanceToDatabase = new Distance();
            distanceToDatabase.setFromCity(fromCity);

            for (City toCity : toCities) {
                double distance = crowFlight(fromCity.getLatitude(), fromCity.getLongitude(), toCity.getLatitude(), toCity.getLongitude());

                distanceToDatabase.setToCity(toCity);
                distanceToDatabase.setDistance(distance);
                crowFlightDistances.add(distanceToDatabase);
                distanceRepository.save(distanceToDatabase);
            }
        }

        return crowFlightDistances;
    }

    public List<Distance> calculateMatrixDistances(DistanceRequest distanceRequest) throws CityNotFoundException {
        List<City> fromCities = dataService.getCitiesByName(distanceRequest.getFromCities());
        List<City> toCities = dataService.getCitiesByName(distanceRequest.getToCities());
        int fromSize = fromCities.size();
        int toSize = toCities.size();

        List<Distance> result = new ArrayList<>();


        for (int i = 0; i < fromSize; i++) {
            for (int j = 0; j < toSize; j++) {
                City fromCity = fromCities.get(i);
                City toCity = toCities.get(j);
                double distance = crowFlight(fromCity.getLatitude(), fromCity.getLongitude(), toCity.getLatitude(), toCity.getLongitude());

                Distance distanceToDatabase = new Distance();
                distanceToDatabase.setFromCity(fromCity);
                distanceToDatabase.setToCity(toCity);
                distanceToDatabase.setDistance(distance);

                distanceRepository.save(distanceToDatabase);


                result.add(distanceToDatabase);
            }
        }

        return result;
    }


    private Double crowFlight(double lat1, double lon1, double lat2, double lon2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371 * b;
    }

}
