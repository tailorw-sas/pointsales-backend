package com.kynsof.calendar.infrastructure.service.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewServiceMessage {
    private String shift;
    private String service;
    private String local;
    private String blockCode;
}
