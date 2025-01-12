package com.harmonious.foundear.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String status;
    private int code;
    private String message;
    private T data;
    private List<ErrorDetails> errors;

    @Setter
    @Getter
    public static class ErrorDetails {
        // Getters and Setters
        private String field;
        private String message;

        public ErrorDetails(String field, String message) {
            this.field = field;
            this.message = message;
        }

    }
}
