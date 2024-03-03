package com.kynsof.store.application.query.getAll;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.application.command.deleted.ISubcategoryService;
import org.springframework.stereotype.Component;

@Component
public class GetAllSubcategoriesQueryHandler implements IQueryHandler<GetAllSubcategoriesQuery, PaginatedResponse> {

    private  ISubcategoryService subcategoryService;

//    public GetAllSubcategoriesQueryHandler(ISubcategoryService subcategoryService) {
//        this.subcategoryService = subcategoryService;
//    }

    @Override
    public PaginatedResponse handle(GetAllSubcategoriesQuery query) {
    //    return subcategoryService.findAll(query.getPageable()).map(SubcategoryResponse::new);
        return null;
    }
}
