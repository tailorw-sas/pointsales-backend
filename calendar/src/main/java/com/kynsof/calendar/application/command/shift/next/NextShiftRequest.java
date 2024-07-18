package com.kynsof.calendar.application.command.shift.next;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NextShiftRequest {
    private UUID local;
    private UUID service;
    private UUID doctor;
}
