package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.infrastructure.entity.Resource;
import com.kynsof.calendar.infrastructure.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AvailabilityByRangeDto {

    private UUID resourceId;
    private String resourceName;
    private String resourceStatus;
    private List<ScheduleTempDto> schedules;

    // Constructor que acepta un Resource y una lista de Schedules
    public AvailabilityByRangeDto(Resource resource, List<Schedule> schedules) {
        this.resourceId = resource.getId();
        this.resourceName = resource.getName();
        this.resourceStatus = resource.getStatus().name();
        this.schedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            this.schedules.add(new ScheduleTempDto(schedule));
        }
    }

    // Getters y Setters
}