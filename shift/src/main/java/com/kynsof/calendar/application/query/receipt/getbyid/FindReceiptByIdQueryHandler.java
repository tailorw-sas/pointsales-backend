package com.kynsof.calendar.application.query.receipt.getbyid;

import com.kynsof.calendar.application.query.ReceiptResponse;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
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
