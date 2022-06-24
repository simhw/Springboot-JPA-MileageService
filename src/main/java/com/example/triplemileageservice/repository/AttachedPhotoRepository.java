package com.example.triplemileageservice.repository;

import com.example.triplemileageservice.model.AttachedPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachedPhotoRepository extends JpaRepository<AttachedPhoto, String> {
}
