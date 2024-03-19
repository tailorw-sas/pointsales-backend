package com.kynsof.calendar.application.command.schedule.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateScheduleRequest {
    private UUID resourceId;
    private UUID businessId;
    private UUID serviceId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int stock;
}