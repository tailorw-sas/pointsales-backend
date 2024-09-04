package com.kynsof.shift.application.query.firebaseToken.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.shift.domain.service.IFirebaseTokenService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchFirebaseTokenQueryHandler implements IQueryHandler<GetSearchFirebaseTokenQuery, PaginatedResponse> {

    private final IFirebaseTokenService service;

    public GetSearchFirebaseTokenQueryHandler(IFirebaseTokenService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchFirebaseTokenQuery query) {

        return this.service.search(query.getPageable(), query.getFilter());
    }
}
