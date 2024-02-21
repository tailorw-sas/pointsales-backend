package com.kynsof.calendar.application.query.qualification.search;

import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchQualificationQueryHandler implements IQueryHandler<GetSearchQualificationQuery, PaginatedResponse>{
    private final IQualificationService service;
    
    public GetSearchQualificationQueryHandler(IQualificationService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchQualificationQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
