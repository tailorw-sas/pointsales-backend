package com.kynsof.scheduled.application.query.schedule.getAll;

import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.share.core.domain.bus.query.IQuery;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

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
