package com.kynsof.store.application.command.subcategory.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.CategoryDto;
import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import com.kynsof.store.domain.services.ICategoryService;
import com.kynsof.store.domain.services.ISubcategoryService;
import org.springframework.stereotype.Component;

@Component
public class UpdateSubcategoryCommandHandler implements ICommandHandler<UpdateSubcategoryCommand> {

    private final ISubcategoryService subcategoryService;
    private final ICategoryService categoryService;

    public UpdateSubcategoryCommandHandler(ISubcategoryService subcategoryService, ICategoryService categoryService) {
        this.subcategoryService = subcategoryService;
        this.categoryService = categoryService;
    }

    @Override
    public void handle(UpdateSubcategoryCommand command) {
        CategoryDto categoryDto = categoryService.findById(command.getCategoryId());
        SubcategoryEntityDto subcategoryDto = new SubcategoryEntityDto(
                command.getSubcategoryId(), command.getName(), command.getDescription(), command.getCategoryId(),categoryDto);
        subcategoryService.update(subcategoryDto);
    }
}
