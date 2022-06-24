package com.example.triplemileageservice.repository;

import com.example.triplemileageservice.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PlaceRepository extends JpaRepository<Place, String> {
    Optional<Place> findById(String id);
}
