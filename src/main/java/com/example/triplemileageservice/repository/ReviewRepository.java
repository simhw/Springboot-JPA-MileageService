package com.example.triplemileageservice.repository;

import com.example.triplemileageservice.model.Place;
import com.example.triplemileageservice.model.Review;
import com.example.triplemileageservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReviewRepository extends JpaRepository<Review, String> {
//     Optional<Review> findByIdAndDeletedIsFalse(String id);
     Set<Review> findAllByUserAndPlace(User user, Place place);
//     List<Review> findByPlaceAndDeletedIsTrue(Place place);
     Set<Review> findByPlace(Place place);
}
