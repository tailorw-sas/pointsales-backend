package com.kynsof.scheduled.application.query;

import com.kynsof.scheduled.domain.dto.*;
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
public class ScheduleResponse {

    private UUID id;
    private ResourceDto resource;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int stock;
    private EStatusSchedule status;

    public ScheduleResponse(ScheduleDto schedule) {
        this.id = schedule.getId();
        this.resource = schedule.getResource();
        this.date = schedule.getDate();
        this.startTime = schedule.getStartTime();
        this.endingTime = schedule.getEndingTime();
        this.stock = schedule.getStock();
        this.status = schedule.getStatus();
    }

}
