package com.kynsof.scheduled.application.command.schedule.createlote;

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
public class CreateScheduleByLoteRequest {
    private List<UUID> idResource;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CreateScheduleRequest> schedules;
}