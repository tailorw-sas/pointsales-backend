package com.kynsof.scheduled.application.query.receipt.getbyid;

import com.kynsof.scheduled.application.query.ReceiptResponse;
import com.kynsof.scheduled.domain.dto.ReceiptDto;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.ReceiptService;
import org.springframework.stereotype.Component;

@Component
public class FindReceiptByIdQueryHandler implements IQueryHandler<FindReceiptByIdQuery, ReceiptResponse>  {

    private final ReceiptService serviceImpl;

    public FindReceiptByIdQueryHandler(ReceiptService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ReceiptResponse handle(FindReceiptByIdQuery query) {
        ReceiptDto response = serviceImpl.findById(query.getId());

        return new ReceiptResponse(response);
    }

}
