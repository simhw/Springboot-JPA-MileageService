package com.example.triplemileageservice.controller;

import com.example.triplemileageservice.dto.EventDto;
import com.example.triplemileageservice.dto.ResponseDto;
import com.example.triplemileageservice.dto.ReviewDto;
import com.example.triplemileageservice.model.PointStatus;
import com.example.triplemileageservice.model.User;
import com.example.triplemileageservice.repository.UserRepository;
import com.example.triplemileageservice.service.point.SavePointService;
import com.example.triplemileageservice.service.point.UpdateUserPointService;
import com.example.triplemileageservice.service.review.DeleteReviewService;
import com.example.triplemileageservice.service.review.UpdateReviewService;
import com.example.triplemileageservice.service.review.WriteReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class EventApiController {
    private final UserRepository userRepository;

    // review service
    private final WriteReviewService writeReviewService;
    private final UpdateReviewService updateReviewService;
    private final DeleteReviewService deleteReviewService;

    // point service
    private final SavePointService savePointService;

    // user service
    private final UpdateUserPointService updateUserPointService;

    @PostMapping("/events")
    public ResponseDto<String> eventController(@RequestBody String strBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        EventDto eventDto = objectMapper.readValue(strBody, EventDto.class);
        String type = eventDto.getType();
        if (type.equals("REVIEW")) {
            ReviewDto reviewDto = objectMapper.readValue(strBody, ReviewDto.class);
            User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(IllegalArgumentException::new);

            // ADD, MOD, DELETE
            String action = reviewDto.getAction();
            int point = 0;
            if (action.equals("ADD")) {
                point = writeReviewService.writeReview(user, reviewDto);
                if (point > 0) {
                    savePointService.savePoint(user, point, PointStatus.SAVE);
                    updateUserPointService.updateUserPoint(user, point);
                }
            } else if (action.equals("MOD")) {
                point = updateReviewService.updateReview(reviewDto);
                if (point < 0) {
                    savePointService.savePoint(user, point, PointStatus.RETRIEVE);
                    updateUserPointService.updateUserPoint(user, point);
                } else if (point > 0) {
                    savePointService.savePoint(user, point, PointStatus.SAVE);
                    updateUserPointService.updateUserPoint(user, point);
                }
            } else if (action.equals("DELETE")) {
                point = deleteReviewService.deleteReview(reviewDto);
                if (point < 0) {
                    savePointService.savePoint(user, point, PointStatus.RETRIEVE);
                    updateUserPointService.updateUserPoint(user, point);
                }
            } else return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다.");
            return new ResponseDto(HttpStatus.OK.value(), "success");
        } else return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다.");
    }
}
