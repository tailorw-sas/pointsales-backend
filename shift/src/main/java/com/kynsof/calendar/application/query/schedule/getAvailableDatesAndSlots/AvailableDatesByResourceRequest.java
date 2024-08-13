package com.kynsof.calendar.application.query.schedule.getAvailableDatesAndSlots;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AvailableDatesByResourceRequest {
    private UUID businessId;
    private LocalDate startDate;
    private LocalDate finalDate;
}
