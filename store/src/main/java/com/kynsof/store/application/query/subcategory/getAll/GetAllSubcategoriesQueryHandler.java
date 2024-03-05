package com.kynsof.store.application.query.subcategory.getAll;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.ISubcategoryService;
import org.springframework.stereotype.Component;

@Component
public class GetAllSubcategoriesQueryHandler implements IQueryHandler<GetAllSubcategoriesQuery, PaginatedResponse> {

    private final ISubcategoryService subcategoryService;

    public GetAllSubcategoriesQueryHandler(ISubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @Override
    public PaginatedResponse handle(GetAllSubcategoriesQuery query) {
        return subcategoryService.search(query.getPageable(), query.getFilter());
    }
}
