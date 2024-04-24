package com.kynsof.calendar.application.command.schedule.createall;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class CreateAllScheduleRequest {
    private UUID resourceId;
    private UUID businessId;
    private UUID serviceId;
    private LocalDate date;
    private List<ScheduleAllRequest> schedules;

}