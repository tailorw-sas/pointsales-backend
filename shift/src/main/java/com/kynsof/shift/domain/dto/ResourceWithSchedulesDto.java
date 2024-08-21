package com.kynsof.shift.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResourceWithSchedulesDto {
    private ResourceDto resource;
    private List<ScheduleDto> schedules;

}
