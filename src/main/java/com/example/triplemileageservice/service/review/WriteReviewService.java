package com.example.triplemileageservice.service.review;

import com.example.triplemileageservice.dto.ReviewDto;
import com.example.triplemileageservice.model.AttachedPhoto;
import com.example.triplemileageservice.model.Place;
import com.example.triplemileageservice.model.Review;
import com.example.triplemileageservice.model.User;
import com.example.triplemileageservice.repository.PlaceRepository;
import com.example.triplemileageservice.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WriteReviewService {
    private ReviewRepository reviewRepository;
    private PlaceRepository placeRepository;

    @Autowired
    public WriteReviewService(ReviewRepository reviewRepository, PlaceRepository placeRepository) {
        this.reviewRepository = reviewRepository;
        this.placeRepository = placeRepository;
    }

    @Transactional
    public int writeReview(User principal, ReviewDto reviewDto) {
        Place place = placeRepository.findById(reviewDto.getPlaceId()).orElseThrow(IllegalArgumentException::new);

        // 이미 작성한 리뷰가 있는 경우
        if (reviewRepository.findAllByUserAndPlace(principal, place).size() > 0)
            throw new IllegalArgumentException("이미 작성한 리뷰가 존재합니다.");

        Review review = Review.builder().id(reviewDto.getReviewId()).content(reviewDto.getContent()).place(place).user(principal).build();
        int point = 0;

        // 텍스트 작성 점수 추가
        if (reviewDto.getContent().length() >= 1)
            point += 1;

        // 사진 첨부 점수 추가
        if (reviewDto.getAttachedPhotoIds().size() > 0) {
            point += 1;
            for (String photoIds : reviewDto.getAttachedPhotoIds()) {
                AttachedPhoto attachedPhoto = new AttachedPhoto();
                attachedPhoto.setId(photoIds);
                review.attachPhoto(attachedPhoto);
            }
        }
        // 특정 장소에 첫 리뷰 작성 점수 추가
        if (reviewRepository.findByPlace(place).isEmpty())
            point += 1;

        reviewRepository.save(review);
        return point;
    }
}
