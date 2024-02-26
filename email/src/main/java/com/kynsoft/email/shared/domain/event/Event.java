package com.kynsoft.email.shared.domain.event;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public abstract class Event<T> {
    private String id;
    private Date date;
    private EventType type;
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    private T data;

    protected Event(EventType type, T data) {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.type = type;
        this.data = data;
    }

    public Event(String id, Date date, EventType type, T data) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
