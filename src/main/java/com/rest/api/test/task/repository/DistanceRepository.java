package com.rest.api.test.task.repository;

import com.rest.api.test.task.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanceRepository extends JpaRepository<Distance,Long> {
}
