package com.kynsof.treatments.application.query.cei10.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.ICie10Service;
import org.springframework.stereotype.Component;

@Component
public class GetSearchCie10QueryHandler implements IQueryHandler<GetSearchCie10Query, PaginatedResponse>{

    private final ICie10Service serviceImpl;

    public GetSearchCie10QueryHandler(ICie10Service serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchCie10Query query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
