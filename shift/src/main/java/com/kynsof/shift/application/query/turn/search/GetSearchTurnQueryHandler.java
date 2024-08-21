package com.kynsof.shift.application.query.turn.search;

import com.kynsof.shift.domain.service.ITurnService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchTurnQueryHandler implements IQueryHandler<GetSearchTurnQuery, PaginatedResponse>{
    private final ITurnService service;
    
    public GetSearchTurnQueryHandler(ITurnService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchTurnQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
