package com.kynsof.calendar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class AvailableTimeSlotDto {
    private LocalTime startTime;
    private LocalTime endingTime;
    private UUID scheduleId;
}
