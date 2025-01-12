package com.harmonious.foundear.response;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {
    public static <T> ResponseEntity<ApiResponse<T>> success(int code, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus("success");
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return ResponseEntity.status(code).body(response);
    }

    public static ResponseEntity<ApiResponse<Object>> error(int code, String message, List<ApiResponse.ErrorDetails> errors) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setStatus("error");
        response.setCode(code);
        response.setMessage(message);
        response.setErrors(errors);
        return ResponseEntity.status(code).body(response);
    }
}
