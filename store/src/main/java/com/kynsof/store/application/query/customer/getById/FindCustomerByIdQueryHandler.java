package com.kynsof.store.application.query.customer.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.application.query.customer.getAll.CustomerResponse;
import com.kynsof.store.domain.dto.CustomerDto;
import com.kynsof.store.domain.services.ICustomerService;
import org.springframework.stereotype.Component;

@Component
public class FindCustomerByIdQueryHandler implements IQueryHandler<FindCustomerByIdQuery, CustomerResponse> {

    private final ICustomerService customerService;


    public FindCustomerByIdQueryHandler(ICustomerService categoryService) {
        this.customerService = categoryService;
    }

    @Override
    public CustomerResponse handle(FindCustomerByIdQuery query) {
        CustomerDto customerDto = customerService.findById(query.getId());
        return new CustomerResponse(customerDto);
    }
}

