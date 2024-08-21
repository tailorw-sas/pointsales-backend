package com.kynsof.shift.application.query.businessService.getresourcebybusiness;

import com.kynsof.shift.domain.service.IBusinessResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetResourcesByIdBusinessQueryHandler implements IQueryHandler<FindResourcesByIdBusinessQuery, PaginatedResponse>{
    private final IBusinessResourceService service;
    
    public GetResourcesByIdBusinessQueryHandler(IBusinessResourceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindResourcesByIdBusinessQuery query) {

        return this.service.findResourceByBusinessId(query.getPageable(), query.getId());
    }
}
