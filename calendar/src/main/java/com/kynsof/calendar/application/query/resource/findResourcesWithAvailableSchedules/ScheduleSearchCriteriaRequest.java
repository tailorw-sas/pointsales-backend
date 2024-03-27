package com.kynsof.calendar.application.query.resource.findResourcesWithAvailableSchedules;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ScheduleSearchCriteriaRequest {
    @NotNull
    private UUID businessId;
    @NotNull
    private UUID serviceId;
    @NotNull
    private LocalDate date;
    private Integer pageSize;
    private Integer page;
}
