package com.kynsof.calendar.application.command.schedule.createGoogleStyle;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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