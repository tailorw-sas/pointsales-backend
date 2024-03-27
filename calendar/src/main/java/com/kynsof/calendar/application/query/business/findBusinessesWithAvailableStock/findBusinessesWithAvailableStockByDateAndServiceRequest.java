package com.kynsof.calendar.application.query.business.findBusinessesWithAvailableStock;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class findBusinessesWithAvailableStockByDateAndServiceRequest {
    private LocalDate date;
    private UUID serviceId;
    private Integer pageSize;
    private Integer page;
}
