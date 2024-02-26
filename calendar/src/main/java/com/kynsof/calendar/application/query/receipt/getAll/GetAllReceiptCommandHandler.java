package com.kynsof.calendar.application.query.receipt.getAll;

import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetAllReceiptCommandHandler implements IQueryHandler<FindReceiptWithFilterQuery, PaginatedResponse>{

    private final IReceiptService service;

    public GetAllReceiptCommandHandler(IReceiptService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindReceiptWithFilterQuery query) {

        return this.service.findAll(query.getPageable(), query.getFilter(), query.getResource(), query.getUser(), query.getService(), query.getSchedule(), query.getDate(), query.getStartDate(), query.getEndDate(), query.getStatus());
    }
}
