package com.kynsof.shift.application.query.business.findDetailedAvailableSchedulesByResourceAndBusinessAndDateRange;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class findDetailedAvailableSchedulesByResourceAndBusinessAndDateRangeRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID serviceId;
    private String businessName;
    private Integer pageSize;
    private Integer page;
}
