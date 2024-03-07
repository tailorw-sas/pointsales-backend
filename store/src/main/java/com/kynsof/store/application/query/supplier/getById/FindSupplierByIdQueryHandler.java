package com.kynsof.store.application.query.supplier.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.application.query.supplier.getAll.SupplierResponse;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.stereotype.Component;

@Component
public class FindSupplierByIdQueryHandler implements IQueryHandler<FindSupplierByIdQuery, SupplierResponse> {

    private final ISupplierService supplierService;

    public FindSupplierByIdQueryHandler(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public SupplierResponse handle(FindSupplierByIdQuery query) {
        SupplierEntityDto supplier = supplierService.findById(query.getId());
        return new SupplierResponse(supplier);
    }
}