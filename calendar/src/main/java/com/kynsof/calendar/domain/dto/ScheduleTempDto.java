package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.infrastructure.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ScheduleTempDto {
    private UUID    id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;

    public ScheduleTempDto(Schedule schedule) {
        this.date = schedule.getDate();
        this.startTime = schedule.getStartTime();
        this.endingTime = schedule.getEndingTime();
    }
}
