package com.kynsof.scheduled.application.query.receipt.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.ReceiptService;
import org.springframework.stereotype.Component;

@Component
public class GetAllReceiptCommandHandler implements IQueryHandler<FindReceiptWithFilterQuery, PaginatedResponse>{

    private final ReceiptService serviceImpl;

    public GetAllReceiptCommandHandler(ReceiptService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(FindReceiptWithFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getFilter(), query.getResource(), query.getUser(), query.getService(), query.getSchedule(), query.getDate(), query.getStartDate(), query.getEndDate(), query.getStatus());
    }
}
