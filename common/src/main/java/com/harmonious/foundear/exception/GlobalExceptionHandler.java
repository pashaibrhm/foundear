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
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return ResponseUtil.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + ex.getMessage(),
                Collections.singletonList(new ApiResponse.ErrorDetails("error", ex.getMessage()))
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseUtil.error(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid input: " + ex.getMessage(),
                Collections.singletonList(new ApiResponse.ErrorDetails("invalidArgument", ex.getMessage()))
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseUtil.error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Collections.singletonList(new ApiResponse.ErrorDetails(ex.getField(), ex.getMessage()))
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex, WebRequest request) {
        return ResponseUtil.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An error occurred: " + ex.getMessage(),
                Collections.singletonList(new ApiResponse.ErrorDetails("error", ex.getMessage()))
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ApiResponse.ErrorDetails> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new ApiResponse.ErrorDetails(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseUtil.error(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );
    }
}
