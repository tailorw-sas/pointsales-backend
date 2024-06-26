package com.kynsof.calendar.application.query.place.search;

import com.kynsof.calendar.domain.service.IBlockService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchPlaceQueryHandler implements IQueryHandler<GetSearchPlaceQuery, PaginatedResponse>{
    private final IBlockService service;
    
    public GetSearchPlaceQueryHandler(IBlockService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchPlaceQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
