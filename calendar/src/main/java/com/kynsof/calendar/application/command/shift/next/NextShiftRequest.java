package com.kynsof.calendar.application.command.shift.next;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NextShiftRequest {
    private String local;
    private String service;
    private String doctor;
}
