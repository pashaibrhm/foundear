package com.harmonious.foundear.exception;

import com.harmonious.foundear.response.ApiResponse;
import com.harmonious.foundear.response.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>("handleRuntimeException - An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>("handleIllegalArgumentException - Invalid input: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse.ErrorDetails errorDetails = new ApiResponse.ErrorDetails(ex.getField(), ex.getMessage());
        return ResponseUtil.error(404, ex.getMessage(), Collections.singletonList(errorDetails));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("handleGenericException - An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ApiResponse.ErrorDetails> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new ApiResponse.ErrorDetails(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseUtil.error(400, "Validation failed", errors);
    }
}
