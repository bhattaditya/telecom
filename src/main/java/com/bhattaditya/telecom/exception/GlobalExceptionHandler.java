package com.bhattaditya.telecom.exception;

import com.bhattaditya.telecom.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException e) {
        String message = e.getMessage();
        Boolean isSuccess = false;

        return new ResponseEntity<>(new ApiResponse(message, isSuccess), HttpStatus.OK);
    }
}
