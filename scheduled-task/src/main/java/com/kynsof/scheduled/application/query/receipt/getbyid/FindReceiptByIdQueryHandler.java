package com.kynsof.scheduled.application.query.receipt.getbyid;

import com.kynsof.scheduled.application.query.ReceiptResponse;
import com.kynsof.scheduled.domain.dto.ReceiptDto;
import com.kynsof.scheduled.domain.service.IReceiptService;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindReceiptByIdQueryHandler implements IQueryHandler<FindReceiptByIdQuery, ReceiptResponse>  {

    private final IReceiptService service;

    public FindReceiptByIdQueryHandler(IReceiptService service) {
        this.service = service;
    }

    @Override
    public ReceiptResponse handle(FindReceiptByIdQuery query) {
        ReceiptDto response = service.findById(query.getId());

        return new ReceiptResponse(response);
    }

}
