package com.kynsof.shift.application.query.place.getById;

import com.kynsof.shift.application.query.PlaceResponse;
import com.kynsof.shift.domain.dto.PlaceDto;
import com.kynsof.shift.domain.service.IPlaceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindPlaceByIdQueryHandler implements IQueryHandler<FindPlaceByIdQuery, PlaceResponse>  {

    private final IPlaceService service;

    public FindPlaceByIdQueryHandler(IPlaceService service) {
        this.service = service;
    }

    @Override
    public PlaceResponse handle(FindPlaceByIdQuery query) {
        PlaceDto response = service.findById(query.getId());

        return new PlaceResponse(response);
    }
}
