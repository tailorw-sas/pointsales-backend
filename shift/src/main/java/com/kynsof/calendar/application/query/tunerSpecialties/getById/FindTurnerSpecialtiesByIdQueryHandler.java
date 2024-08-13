package com.kynsof.calendar.application.query.tunerSpecialties.getById;

import com.kynsof.calendar.application.query.TurnerSpecialtiesResponse;
import com.kynsof.calendar.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.calendar.domain.service.ITurnerSpecialtiesService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindTurnerSpecialtiesByIdQueryHandler implements IQueryHandler<FindTurnerSpecialtiesByIdQuery, TurnerSpecialtiesResponse>  {

    private final ITurnerSpecialtiesService service;

    public FindTurnerSpecialtiesByIdQueryHandler(ITurnerSpecialtiesService service) {
        this.service = service;
    }

    @Override
    public TurnerSpecialtiesResponse handle(FindTurnerSpecialtiesByIdQuery query) {
        TurnerSpecialtiesDto response = service.findById(query.getId());

        return new TurnerSpecialtiesResponse(response);
    }
}
