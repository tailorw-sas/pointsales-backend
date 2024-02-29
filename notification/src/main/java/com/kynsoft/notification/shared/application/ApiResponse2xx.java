package com.kynsoft.notification.shared.application;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class ApiResponse2xx<T> {
    private String timestamp;
    private int status;
    private String message;
    private String errors;
    private T data;

    public ApiResponse2xx(T data, HttpStatus status) {
        this.timestamp = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss '['z']'")
                .format(ZonedDateTime.now(ZoneId.of("UTC")));
        this.status = status.value();
        this.message = "OK";
        this.errors = null;
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }

}
