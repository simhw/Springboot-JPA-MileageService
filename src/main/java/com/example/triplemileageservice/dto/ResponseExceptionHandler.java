package com.example.triplemileageservice.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResponseExceptionHandler {
    // 예외 처리마다 클래스 생성이 가능하다.
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseDto<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
