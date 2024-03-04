package com.kynsof.store.application.query.subcategory.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.application.query.subcategory.getAll.SubcategoryResponse;
import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import com.kynsof.store.domain.services.ISubcategoryService;
import org.springframework.stereotype.Component;

@Component
public class FindSubcategoryByIdQueryHandler implements IQueryHandler<FindSubcategoryByIdQuery, SubcategoryResponse> {

    private final ISubcategoryService subcategoryService;

    public FindSubcategoryByIdQueryHandler(ISubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @Override
    public SubcategoryResponse handle(FindSubcategoryByIdQuery query) {
        SubcategoryEntityDto subcategory = subcategoryService.findById(query.getId());
        return new SubcategoryResponse(subcategory);
    }
}

