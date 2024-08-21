package com.kynsof.shift.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class ScheduleAvailabilityDto {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private UUID scheduleId;
}
