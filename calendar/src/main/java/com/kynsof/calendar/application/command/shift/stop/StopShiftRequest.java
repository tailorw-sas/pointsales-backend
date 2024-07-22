package com.kynsof.calendar.application.command.shift.stop;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StopShiftRequest {
    private String local;
    private String service;
    private String doctor;
}
