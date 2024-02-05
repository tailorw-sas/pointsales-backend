package com.kynsof.treatments.infrastructure.exceptions;

public class MethodArgumentTypeMismatchException extends RuntimeException {

    public MethodArgumentTypeMismatchException(String message) {
        super(message);
    }
}
