package com.kynsof.store.application.query.product;


import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.domain.dto.ProductEntityDto;
import com.kynsof.store.domain.services.IProductService;
import org.springframework.stereotype.Component;

@Component
public class FindProductsByIdQueryHandler implements IQueryHandler<FindProductsByIdQuery, ProductResponse> {

    private final IProductService productService;

    public FindProductsByIdQueryHandler(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductResponse handle(FindProductsByIdQuery query) {
        ProductEntityDto product = productService.findById(query.getId());
        return new ProductResponse(product);
    }

}
