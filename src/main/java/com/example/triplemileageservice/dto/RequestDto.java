package com.example.triplemileageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
public class RequestDto {
    @Data
    public class EventDto {
        private String type;
        private String action;
    }

    @Data
    public class ReviewDto {
        private String reviewId;
        private String content;
        private List<String> attachedPhotoIds;
        private String userId;
        private String placeId;
    }
}
