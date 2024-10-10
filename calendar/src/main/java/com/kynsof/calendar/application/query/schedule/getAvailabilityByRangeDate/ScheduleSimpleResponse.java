package com.kynsof.calendar.application.query.schedule.getAvailabilityByRangeDate;

import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleSimpleResponse implements IResponse {

    private UUID id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int stock;
    private EStatusSchedule status;

    public ScheduleSimpleResponse(ScheduleDto schedule) {
        this.id = schedule.getId();
        this.date = schedule.getDate();
        this.startTime = schedule.getStartTime();
        this.endingTime = schedule.getEndingTime();
        this.stock = schedule.getStock();
        this.status = schedule.getStatus();
    }

}
