package com.kynsof.store.application.query.getAll;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.stereotype.Component;

@Component
public class GetAllSuppliersQueryHandler implements IQueryHandler<GetAllSuppliersQuery, PaginatedResponse> {

    private  ISupplierService supplierService;

//    public GetAllSuppliersQueryHandler(ISupplierService supplierService) {
//        this.supplierService = supplierService;
//    }

    @Override
    public PaginatedResponse handle(GetAllSuppliersQuery query) {
        //return supplierService.findAll(query.getPageable()).map(SupplierResponse::new);
        return null;
    }
}
