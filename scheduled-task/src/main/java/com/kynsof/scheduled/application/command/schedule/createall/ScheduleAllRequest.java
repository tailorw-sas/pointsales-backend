package com.kynsof.scheduled.application.command.schedule.createall;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAllRequest {
    private LocalTime startTime;
    private LocalTime endingTime;
}