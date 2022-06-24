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
public class UpdateReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public int updateReview(ReviewDto reviewDto) {
        int point = 0;
        Review writtenReview = reviewRepository.findById(reviewDto.getReviewId()).orElseThrow(IllegalArgumentException::new);

        boolean preIsChar = writtenReview.getContent().length() > 0 ? true : false;
        boolean preIsPhoto = writtenReview.getAttachedPhotos().size() > 0 ? true : false;
        boolean nowIsChar = reviewDto.getContent().length() > 0 ? true : false;
        boolean nowIsPhoto = reviewDto.getAttachedPhotoIds().size() > 0 ? true : false;

        // 포인트 계산
        if (preIsChar == true && nowIsChar == false) point -= 1;
        if (preIsPhoto == true && nowIsPhoto == false) point -= 1;

        if (preIsChar == false && nowIsChar == true) point += 1;
        if (preIsPhoto == false && nowIsPhoto == true) point += 1;


        // 리뷰 수정
        // CASE 1: 이미지 배열 전체 삭제 후 다시 삽입
        // CASE 2: 새롭게 추가된 이미지와 삭제할 이미지 구분

        if (reviewDto.getAction().equals("MOD")) {
            writtenReview.setContent(reviewDto.getContent());
            Set<String> updatedPhotoIds = reviewDto.getAttachedPhotoIds();
            Set<AttachedPhoto> attachedPhotos = new HashSet<>(writtenReview.getAttachedPhotos());
            for (AttachedPhoto attachedPhoto : attachedPhotos) {
                // 새로운 이미지 배열에 없는 이미지 삭제
                if (!reviewDto.getAttachedPhotoIds().contains(attachedPhoto.getId())) {
                    writtenReview.deletePhotos(attachedPhoto);
                }
            }

            for (String attachedPhotoIds : reviewDto.getAttachedPhotoIds()) {
                // 새로운 이미지 추가
                AttachedPhoto attachedPhoto = new AttachedPhoto();
                attachedPhoto.setId(attachedPhotoIds);
                writtenReview.attachPhoto(attachedPhoto);
            }
        }
        return point;
    }
}