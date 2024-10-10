package com.kynsof.calendar.application.query.schedule.getAvailabilityByRangeDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ResourceWithSchedulesResponse {
    private UUID resourceId;
    private String resourceName;
    private String image;
    private List<ScheduleSimpleResponse> schedules;
}