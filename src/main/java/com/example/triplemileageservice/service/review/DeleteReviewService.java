package com.example.triplemileageservice.service.review;

import com.example.triplemileageservice.dto.ReviewDto;
import com.example.triplemileageservice.model.AttachedPhoto;
import com.example.triplemileageservice.model.Review;
import com.example.triplemileageservice.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class DeleteReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public int deleteReview(ReviewDto reviewDto) {
        int point = 0;
        Review writtenReview = reviewRepository.findById(reviewDto.getReviewId()).orElseThrow(IllegalArgumentException::new);

        boolean preIsChar = writtenReview.getContent().length() > 0 ? true : false;
        boolean preIsPhoto = writtenReview.getAttachedPhotos().size() > 0 ? true : false;
        boolean nowIsChar = false;
        boolean nowIsPhoto = false;

        // 포인트 계산
        if (preIsChar == true && nowIsChar == false) point -= 1;
        if (preIsPhoto == true && nowIsPhoto == false) point -= 1;

        reviewRepository.delete(writtenReview);
        return point;
    }
}
