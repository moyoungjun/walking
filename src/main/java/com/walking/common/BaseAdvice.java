package com.walking.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseAdvice {

    @ExceptionHandler(value = {BaseException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(BaseException e) {
        log.error("handleBaseException throw BaseException : {}", e.getResponseCode());
        return ErrorResponse.toResponseEntity(e.getResponseCode());
    }
}
