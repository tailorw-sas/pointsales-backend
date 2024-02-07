package com.kynsof.scheduled.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleDto {

    private UUID id;

    private ResourceDto resource;

    @JsonIgnoreProperties({"logo", "description", "resources", "services", "schedules", "receipts"})
    private BusinessDto business;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endingTime;

    private int stock;

    private int initialStock;

    private EStatusSchedule status;

    public ScheduleDto(UUID id, ResourceDto resource, LocalDate date, LocalTime startTime, LocalTime endingTime, int stock, EStatusSchedule status) {
        this.id = id;
        this.resource = resource;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.stock = stock;
        this.status = status;
    }

    public ScheduleDto(UUID id, ResourceDto resource, BusinessDto business, LocalDate date, LocalTime startTime, LocalTime endingTime, int stock) {
        this.id = id;
        this.resource = resource;
        this.business = business;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.stock = stock;
    }

}
