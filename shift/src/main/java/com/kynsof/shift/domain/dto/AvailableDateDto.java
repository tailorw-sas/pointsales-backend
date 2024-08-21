package com.kynsof.shift.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AvailableDateDto {
    private LocalDate date;
    private List<AvailableTimeSlotDto> timeSlots;
}
