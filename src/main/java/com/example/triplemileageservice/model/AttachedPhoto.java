package com.example.triplemileageservice.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString(exclude = "review")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AttachedPhoto {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((AttachedPhoto)obj).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
