package com.kynsof.treatments.application.query.consultHistory;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IConsultHistoryService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchConsultHistoryQueryHandler implements IQueryHandler<GetSearchConsultHistoryQuery, PaginatedResponse>{
    private final IConsultHistoryService service;
    
    public GetSearchConsultHistoryQueryHandler(IConsultHistoryService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchConsultHistoryQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
