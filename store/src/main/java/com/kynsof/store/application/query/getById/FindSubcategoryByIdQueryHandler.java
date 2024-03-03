package com.kynsof.store.application.query.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.application.command.deleted.ISubcategoryService;
import com.kynsof.store.application.query.getAll.SubcategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindSubcategoryByIdQueryHandler implements IQueryHandler<FindSubcategoryByIdQuery, SubcategoryResponse> {

    private  ISubcategoryService subcategoryService;

//    @Autowired
//    public FindSubcategoryByIdQueryHandler(ISubcategoryService subcategoryService) {
//        this.subcategoryService = subcategoryService;
//    }

    @Override
    public SubcategoryResponse handle(FindSubcategoryByIdQuery query) {
//        SubcategoryDto subcategory = subcategoryService.findById(query.getId());
//        return new SubcategoryResponse(subcategory);
        return null;
    }
}

