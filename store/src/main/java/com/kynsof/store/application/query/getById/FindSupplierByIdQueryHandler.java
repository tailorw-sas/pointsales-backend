package com.kynsof.store.application.query.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.domain.services.ISupplierService;
import com.kynsof.store.application.query.getAll.SupplierResponse;
import org.springframework.stereotype.Component;

@Component
public class FindSupplierByIdQueryHandler implements IQueryHandler<FindSupplierByIdQuery, SupplierResponse> {

    private  ISupplierService supplierService;

//    @Autowired
//    public FindSupplierByIdQueryHandler(ISupplierService supplierService) {
//        this.supplierService = supplierService;
//    }

    @Override
    public SupplierResponse handle(FindSupplierByIdQuery query) {
//        SupplierDto supplier = supplierService.findById(query.getId());
//        return new SupplierResponse(supplier);
        return null;
    }
}
