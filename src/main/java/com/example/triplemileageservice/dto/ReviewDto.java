package com.example.triplemileageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto extends EventDto {
    private String reviewId;
    private String content;
    private String userId;
    private String placeId;
    private Set<String> attachedPhotoIds;
}
