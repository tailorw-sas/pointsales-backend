package com.kynsof.shift.application.command.shift.next;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NextShiftRequest {
    private String local;
    private List<String> service;
    private String doctor;
    private String lastShift;
}
