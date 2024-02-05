package com.kynsof.scheduled.application.command.schedule.createall;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCreateAllRequest {
    private UUID idResource;
    private LocalDate date;
    private List<ScheduleRequest> schedules;
}