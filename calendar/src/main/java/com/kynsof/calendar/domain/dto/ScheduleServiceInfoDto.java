package com.kynsof.calendar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleServiceInfoDto {
    private UUID scheduledId;
    private UUID businessId;
    private UUID resourceId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private String businessName;
    private String businessAddress;
    private String businessLogo;
    private Double servicePrice;
    private String latitude;
    private String longitude;
}
