package com.kynsof.calendar.application.command.schedule.createall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAllRequest {
    private LocalTime startTime;
    private LocalTime endingTime;
}