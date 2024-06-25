package com.example.portalsecurity.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException apiRequestException) {
        ApiException apiException = new ApiException(
                apiRequestException.getMessage(), apiRequestException, HttpStatus.BAD_REQUEST, ZonedDateTime.now());

        return ResponseEntity
                .status(apiException.httpStatus())
                .header("message", apiException.message())
                .body(apiException);

        //return new ResponseEntity<>(apiException, apiException.httpStatus());
    }
}