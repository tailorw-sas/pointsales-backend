package com.kynsof.store.application.query.customer.getAll;


import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.ICustomerService;
import org.springframework.stereotype.Component;

@Component
public class GetAllCustomerQueryHandler implements IQueryHandler<GetAllCustomerQuery, PaginatedResponse> {

    private final ICustomerService customerService;

    public GetAllCustomerQueryHandler(ICustomerService categoryService) {
        this.customerService = categoryService;
    }

    @Override
    public PaginatedResponse handle(GetAllCustomerQuery query) {
        return customerService.search(query.getPageable(), query.getFilter());
    }
}
