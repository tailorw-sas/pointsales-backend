package com.kynsof.calendar.application.query.schedule.getAll;

import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
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
public class FindScheduleWithFilterQuery implements IQuery {

    private Pageable pageable;
    private UUID idResource;
    private LocalDate date;
    private LocalDate startDate;
    private LocalDate endDate;
    private EStatusSchedule status;

}
