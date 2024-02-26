package com.kynsof.calendar.application.command.schedule.createlote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleRequest {
    private LocalTime startTime;
    private LocalTime endingTime;
}