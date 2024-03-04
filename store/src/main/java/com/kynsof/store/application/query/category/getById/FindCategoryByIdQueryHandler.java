package com.kynsof.store.application.query.category.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.domain.dto.CategoryDto;
import com.kynsof.store.domain.services.ICategoryService;
import com.kynsof.store.application.query.category.getAll.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class FindCategoryByIdQueryHandler implements IQueryHandler<FindCategoryByIdQuery, CategoryResponse> {

    private final ICategoryService categoryService;


    public FindCategoryByIdQueryHandler(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryResponse handle(FindCategoryByIdQuery query) {
        CategoryDto category = categoryService.findById(query.getId());
        return new CategoryResponse(category);
    }
}

