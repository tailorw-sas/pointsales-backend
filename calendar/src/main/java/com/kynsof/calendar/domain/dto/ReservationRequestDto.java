package com.kynsof.calendar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ReservationRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID serviceId;
    private UUID businessId; // Opcional
}
