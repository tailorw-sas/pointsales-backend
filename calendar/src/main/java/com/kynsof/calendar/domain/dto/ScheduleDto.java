package com.kynsof.calendar.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleDto implements Serializable {

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
    private ServiceDto service;

//    public ScheduleDto(UUID id, ResourceDto resource, LocalDate date, LocalTime startTime, LocalTime endingTime, int stock, EStatusSchedule status,
//                       ServiceDto service) {
//        this.id = id;
//        this.resource = resource;
//        this.date = date;
//        this.startTime = startTime;
//        this.endingTime = endingTime;
//        this.stock = stock;
//        this.status = status;
//        this.service = service;
//    }

//    public ScheduleDto(UUID id, ResourceDto resource, BusinessDto business, LocalDate date, LocalTime startTime,
//                       LocalTime endingTime, int stock) {
//        this.id = id;
//        this.resource = resource;
//        this.business = business;
//        this.date = date;
//        this.startTime = startTime;
//        this.endingTime = endingTime;
//        this.stock = stock;
//    }

}
