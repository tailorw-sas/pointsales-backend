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
public class CreateScheduleAllRequest {
    private UUID idResource;
    private UUID idBusiness;
    private LocalDate date;
    private List<ScheduleAllRequest> schedules;
}