package com.kynsof.calendar.application.query.receipt.search;

import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchReceiptQueryHandler implements IQueryHandler<GetSearchReceiptQuery, PaginatedResponse>{
    private final IReceiptService service;
    
    public GetSearchReceiptQueryHandler(IReceiptService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchReceiptQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
