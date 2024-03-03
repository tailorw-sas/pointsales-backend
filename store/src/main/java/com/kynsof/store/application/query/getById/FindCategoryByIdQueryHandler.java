package com.kynsof.store.application.query.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.application.command.deleted.ICategoryService;
import com.kynsof.store.application.query.getAll.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindCategoryByIdQueryHandler implements IQueryHandler<FindCategoryByIdQuery, CategoryResponse> {

    private  ICategoryService categoryService;

//    @Autowired
//    public FindCategoryByIdQueryHandler(ICategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @Override
    public CategoryResponse handle(FindCategoryByIdQuery query) {
//        CategoryDto category = categoryService.findById(query.getId());
//        return new CategoryResponse(category);
        return null;
    }
}

