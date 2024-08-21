package com.kynsof.shift.application.query.block.search;

import com.kynsof.shift.domain.service.IBlockService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchBlockQueryHandler implements IQueryHandler<GetSearchBlockQuery, PaginatedResponse>{
    private final IBlockService service;
    
    public GetSearchBlockQueryHandler(IBlockService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchBlockQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
