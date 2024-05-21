package com.kynsof.identity.application.query.paymentdev.getbyid;

import com.kynsof.identity.domain.dto.PaymentDevDto;
import com.kynsof.identity.domain.interfaces.service.IPaymentDevService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindPaymentDevByIdQueryHandler implements IQueryHandler<FindPaymentDevByIdQuery, PaymentDevResponse>  {

    private final IPaymentDevService service;

    public FindPaymentDevByIdQueryHandler(IPaymentDevService service) {
        this.service = service;
    }

    @Override
    public PaymentDevResponse handle(FindPaymentDevByIdQuery query) {
        PaymentDevDto response = service.findById(query.getId());

        return new PaymentDevResponse(response);
    }
}
