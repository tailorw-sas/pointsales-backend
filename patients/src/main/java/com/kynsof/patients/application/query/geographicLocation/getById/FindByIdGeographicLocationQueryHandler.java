package com.kynsof.patients.application.query.geographicLocation.getById;

import com.kynsof.patients.application.query.geographicLocation.getall.GeographicLocationResponse;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdGeographicLocationQueryHandler implements IQueryHandler<FindByIdGeographicLocationQuery, GeographicLocationResponse>  {

    private final IGeographicLocationService serviceImpl;

    public FindByIdGeographicLocationQueryHandler(IGeographicLocationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public GeographicLocationResponse handle(FindByIdGeographicLocationQuery query) {
        GeographicLocationDto contactInfoDto = serviceImpl.findById(query.getId());

        return new GeographicLocationResponse(contactInfoDto);
    }
}
