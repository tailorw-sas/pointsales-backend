package com.kynsof.calendar.application.command.schedule.update;

import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ScheduleUpdateRequest {
    private UUID resource;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private EStatusSchedule status;
    private int stock;
}