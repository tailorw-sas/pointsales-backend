package com.kynsof.identity.application.query.paymentdev.search;

import com.kynsof.identity.domain.interfaces.service.IPaymentDevService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchPaymentDevQueryHandler implements IQueryHandler<GetSearchPaymentDevQuery, PaginatedResponse>{
    private final IPaymentDevService service;
    
    public GetSearchPaymentDevQueryHandler(IPaymentDevService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchPaymentDevQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
