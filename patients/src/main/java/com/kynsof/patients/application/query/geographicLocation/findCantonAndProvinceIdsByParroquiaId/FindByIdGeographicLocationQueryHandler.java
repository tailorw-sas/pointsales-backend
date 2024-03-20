package com.kynsof.patients.application.query.geographicLocation.findCantonAndProvinceIdsByParroquiaId;

import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;


@Component
public class FindByIdGeographicLocationQueryHandler implements IQueryHandler<FindByIdGeographicLocationQuery, LocationHierarchyResponse> {

    private final IGeographicLocationService serviceImpl;

    public FindByIdGeographicLocationQueryHandler(IGeographicLocationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public LocationHierarchyResponse handle(FindByIdGeographicLocationQuery query) {
        return new LocationHierarchyResponse(serviceImpl.findCantonAndProvinceIdsByParroquiaId(query.getId()));
    }

}
