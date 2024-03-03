package com.kynsof.store.application.query.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.application.command.deleted.IProductService;
import com.kynsof.store.application.query.getAll.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindProductByIdQueryHandler implements IQueryHandler<FindProductByIdQuery, ProductResponse> {

    private  IProductService productService;

//    @Autowired
//    public FindProductByIdQueryHandler(IProductService productService) {
//        this.productService = productService;
//    }

    @Override
    public ProductResponse handle(FindProductByIdQuery query) {
//        ProductDto product = productService.findById(query.getId());
//        return new ProductResponse(product);
        return null;
    }
}
