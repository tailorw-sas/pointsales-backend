package com.kynsof.calendar.application.command.schedule.createlote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleByLoteRequest {
    private List<UUID> idResource;
    private UUID idBusiness;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CreateScheduleRequest> schedules;
}