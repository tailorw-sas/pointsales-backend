package com.kynsof.calendar.application.command.schedule.createGoogleStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class CreateScheduleByLoteGoogleStyleRequest {
    private UUID business;
    private UUID service;
    private UUID resource;
    private List<ScheduleRequest> days;

    public CreateScheduleByLoteGoogleStyleRequest() {
    }
}