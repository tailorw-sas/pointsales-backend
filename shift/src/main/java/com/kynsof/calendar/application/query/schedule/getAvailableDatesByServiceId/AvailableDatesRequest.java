package com.kynsof.calendar.application.query.schedule.getAvailableDatesByServiceId;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AvailableDatesRequest {
    private UUID serviceId;
    private LocalDate startDate;
    private LocalDate finalDate;
}
