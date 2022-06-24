package com.example.triplemileageservice.repository;

import com.example.triplemileageservice.model.Point;
import com.example.triplemileageservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByUser(User user);
}
