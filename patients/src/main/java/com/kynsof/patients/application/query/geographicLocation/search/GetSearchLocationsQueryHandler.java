package com.kynsof.patients.application.query.geographicLocation.search;

import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchLocationsQueryHandler implements IQueryHandler<GetSearchLocationsQuery, PaginatedResponse>{

    private final IGeographicLocationService serviceImpl;

    public GetSearchLocationsQueryHandler(IGeographicLocationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchLocationsQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
