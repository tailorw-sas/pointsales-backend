package com.kynsof.store.application.query.supplier.getAll;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.stereotype.Component;

@Component
public class GetAllSuppliersQueryHandler implements IQueryHandler<GetAllSuppliersQuery, PaginatedResponse> {

    private final ISupplierService supplierService;

    public GetAllSuppliersQueryHandler(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public PaginatedResponse handle(GetAllSuppliersQuery query) {
        return supplierService.search(query.getPageable(), query.getFilter());

    }
}
