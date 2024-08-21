package com.kynsof.shift.application.query.tunerSpecialties.search;

import com.kynsof.shift.domain.service.ITurnerSpecialtiesService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchTurnerSpecialtiesQueryHandler implements IQueryHandler<GetSearchTurnerSpecialtiesQuery, PaginatedResponse>{
    private final ITurnerSpecialtiesService service;
    
    public GetSearchTurnerSpecialtiesQueryHandler(ITurnerSpecialtiesService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchTurnerSpecialtiesQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
