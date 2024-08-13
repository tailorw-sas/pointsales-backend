package com.kynsof.calendar.application.query.place.getById;

import com.kynsof.calendar.application.query.PlaceResponse;
import com.kynsof.calendar.domain.dto.PlaceDto;
import com.kynsof.calendar.domain.service.IPlaceService;
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
