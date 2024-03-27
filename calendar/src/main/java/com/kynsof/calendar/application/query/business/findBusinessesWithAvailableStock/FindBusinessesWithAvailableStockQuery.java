package com.kynsof.calendar.application.query.business.findBusinessesWithAvailableStock;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class FindBusinessesWithAvailableStockQuery implements IQuery {

    private Pageable pageable;
    private LocalDate date;
    private UUID serviceId;
}
