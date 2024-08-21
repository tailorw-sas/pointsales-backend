package com.kynsof.shift.domain.dto;

import com.kynsof.shift.domain.dto.enumType.EStatusSchedule;
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

    private BusinessDto business;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endingTime;

    private int stock;

    private int initialStock;

    private EStatusSchedule status;
    private ServiceDto service;

}
