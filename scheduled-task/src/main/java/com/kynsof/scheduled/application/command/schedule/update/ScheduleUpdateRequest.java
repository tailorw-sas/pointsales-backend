package com.kynsof.scheduled.application.command.schedule.update;

import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateRequest {
    private UUID id;
    private UUID idResource;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private EStatusSchedule status;
}