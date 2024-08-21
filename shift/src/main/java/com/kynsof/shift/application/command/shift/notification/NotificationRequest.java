package com.kynsof.shift.application.command.shift.notification;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
    private String local;
    private String service;
    private String shift;
}
