package com.kynsof.scheduled.infrastructure.exceptions;

public class MethodArgumentTypeMismatchException extends RuntimeException {

    public MethodArgumentTypeMismatchException(String message) {
        super(message);
    }
}
