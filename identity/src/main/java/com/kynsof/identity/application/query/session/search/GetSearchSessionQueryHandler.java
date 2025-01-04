package com.kynsof.identity.application.query.session.search;

import com.kynsof.identity.domain.interfaces.service.ISessionService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchSessionQueryHandler implements
        IQueryHandler<GetSearchSessionQuery, PaginatedResponse> {
    private final ISessionService service;

    public GetSearchSessionQueryHandler(ISessionService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchSessionQuery query) {
        return this.service.search(query.getPageable(), query.getFilter());
    }
}
