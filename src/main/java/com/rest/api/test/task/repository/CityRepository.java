package com.rest.api.test.task.repository;

import com.rest.api.test.task.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
