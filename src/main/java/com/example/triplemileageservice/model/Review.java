package com.example.triplemileageservice.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    private String id;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;

    @Builder.Default
    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AttachedPhoto> attachedPhotos = new HashSet<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public void attachPhoto(AttachedPhoto attachedPhoto) {
        this.attachedPhotos.add(attachedPhoto);
        attachedPhoto.setReview(this);
    }

    public void deletePhotos(AttachedPhoto deletePhoto) {
        this.attachedPhotos.remove(deletePhoto);
        deletePhoto.setReview(null);
    }
}

