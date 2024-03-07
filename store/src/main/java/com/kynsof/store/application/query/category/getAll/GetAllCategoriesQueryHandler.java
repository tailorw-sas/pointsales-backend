package com.kynsof.store.application.query.category.getAll;


import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.ICategoryService;
import org.springframework.stereotype.Component;

@Component
public class GetAllCategoriesQueryHandler implements IQueryHandler<GetAllCategoriesQuery, PaginatedResponse> {

    private final ICategoryService categoryService;

    public GetAllCategoriesQueryHandler(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public PaginatedResponse handle(GetAllCategoriesQuery query) {
        return categoryService.search(query.getPageable(), query.getFilter());
    }
}