package com.kynsof.calendar.application.command.schedule.update;

import com.kynsof.calendar.domain.dto.EStatusSchedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateRequest {
    private UUID id;
    private UUID idResource;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private EStatusSchedule status;
}