package com.example.crypto_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

//package com.example.crypto_api;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.time.LocalDateTime;
//
//@RestControllerAdvice
//public class ValidationExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
//                .map(error -> {
//                    Map<String, String> errorDetails = new HashMap<>();
//                    errorDetails.put("field", error.getField());
//                    errorDetails.put("message", error.getDefaultMessage());
//                    return errorDetails;
//                })
//                .collect(Collectors.toList());
//
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("timestamp", LocalDateTime.now());
//        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
//        errorResponse.put("errors", errors);
//        return errorResponse;
//    }
//}