package com.kynsof.calendar.application.query.schedule.getAvailabilityByRangeDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAvailabilityByRangeDateRequest {
    private Integer pageSize;
    private Integer page;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID serviceId;
    private UUID businessId;
}
