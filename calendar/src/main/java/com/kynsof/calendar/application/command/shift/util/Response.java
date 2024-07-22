package com.kynsof.calendar.application.command.shift.util;


public class Response<T> {
    private ResponseCode code;
    private String description;
    private T value;

    public Response() {
        this.code = ResponseCode.SUCCESS;
        this.description = "";
    }

    public Response(ResponseCode code, String description) {
        this.code = code;
        this.description = description;
    }

    public Response(ResponseCode code, String description, T value) {
        this.code = code;
        this.description = description;
        this.value = value;
    }

    // Getters and setters
    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}