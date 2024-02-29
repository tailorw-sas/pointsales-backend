package com.kynsoft.notification.shared.infrastructure.exceptions;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss '['z']'")
    private ZonedDateTime timestamp;

    private int status;

    private String message;

    private List<String> errors;

    private List<Object> data;

    public ApiError(ZonedDateTime timestamp, int status, String message, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.data = null;
    }

    public ApiError(ZonedDateTime timestamp, HttpStatus status, String message, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status.value();
        this.message = message;
        this.errors = errors;
        this.data = null;
    }

    public ApiError(ZonedDateTime timestamp, int status, String message, List<String> errors,
            List<Object> data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<Object> getData() {
        return data;
    }
}
