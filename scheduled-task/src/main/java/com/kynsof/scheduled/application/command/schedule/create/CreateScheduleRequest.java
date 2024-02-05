package com.kynsof.scheduled.application.command.schedule.create;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleRequest {
    private UUID idResource;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int stock;
}