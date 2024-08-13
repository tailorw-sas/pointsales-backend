package com.kynsof.calendar.application.command.schedule.createGoogleStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    private LocalDateTime start;
    private LocalDateTime end;
    private int stock;
}