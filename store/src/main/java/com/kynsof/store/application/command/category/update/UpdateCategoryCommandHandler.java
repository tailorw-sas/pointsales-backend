package com.kynsof.store.application.command.category.update;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.CategoryDto;
import com.kynsof.store.domain.services.ICategoryService;
import org.springframework.stereotype.Component;

@Component
public class UpdateCategoryCommandHandler implements ICommandHandler<UpdateCategoryCommand> {

    private final ICategoryService categoryService;

    public UpdateCategoryCommandHandler(ICategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @Override
    public void handle(UpdateCategoryCommand command) {
        categoryService.update(new CategoryDto(command.getCategoryId(), command.getName(), command.getDescription()));
    }
}

