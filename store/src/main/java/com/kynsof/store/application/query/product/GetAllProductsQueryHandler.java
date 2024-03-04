package com.kynsof.store.application.query.product;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.IProductService;
import org.springframework.stereotype.Component;

@Component
public class GetAllProductsQueryHandler implements IQueryHandler<GetAllProductsQuery, PaginatedResponse> {

    private final IProductService productService;
    public GetAllProductsQueryHandler(IProductService productService) {
        this.productService = productService;
    }
    @Override
    public PaginatedResponse handle(GetAllProductsQuery query) {
        return productService.search(query.getPageable(), query.getFilter());
    }
}
