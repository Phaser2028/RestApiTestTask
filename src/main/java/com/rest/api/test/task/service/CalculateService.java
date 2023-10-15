package com.rest.api.test.task.service;

import com.rest.api.test.task.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateService {



    public double[][] calculateDistanceMatrix(List<City> cities) {
        int size = cities.size();
        double[][] distanceMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0.0;
                } else {
                    City city1 = cities.get(i);
                    City city2 = cities.get(j);
                    double distance = crowFlight(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude());
                    distanceMatrix[i][j] = distance;
                }
            }
        }

        return distanceMatrix;
    }

    public Double crowFlight(double lat1, double lon1, double lat2, double lon2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371 * b;
    }

}
