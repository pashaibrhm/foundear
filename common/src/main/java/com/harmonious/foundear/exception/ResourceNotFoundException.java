package com.harmonious.foundear.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String field;

    public ResourceNotFoundException(String message, String field, Object value) {
        super(String.format("%s: %s [%s]", message, field, value));
        this.field = field;
    }
}
