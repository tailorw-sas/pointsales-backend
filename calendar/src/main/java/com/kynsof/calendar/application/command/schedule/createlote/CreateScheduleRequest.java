package com.kynsof.calendar.application.command.schedule.createlote;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleRequest {
    private LocalTime startTime;
    private LocalTime endingTime;
}