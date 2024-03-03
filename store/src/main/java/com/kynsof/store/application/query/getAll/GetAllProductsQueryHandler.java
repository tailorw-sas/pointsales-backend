package com.kynsof.store.application.query.getAll;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.application.command.deleted.IProductService;
import org.springframework.stereotype.Component;

@Component
public class GetAllProductsQueryHandler implements IQueryHandler<GetAllProductsQuery, PaginatedResponse>{

    private  IProductService productService;

//    public GetAllProductsQueryHandler(IProductService productService) {
//        this.productService = productService;
//    }

    @Override
    public PaginatedResponse handle(GetAllProductsQuery query) {
       // return productService.findAll(query.getPageable()).map(ProductResponse::new);
        return null;
    }
}
