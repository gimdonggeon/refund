package com.sparta.currency_user.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", illegalArgumentException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFonudException(EntityNotFoundException entityNotFoundException) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", entityNotFoundException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException illegalStateException) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", illegalStateException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "예상치 못한 오류가 발생하였습니다.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
