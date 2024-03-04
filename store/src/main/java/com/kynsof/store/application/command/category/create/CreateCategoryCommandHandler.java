package com.kynsof.store.application.command.category.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.CategoryDto;
import com.kynsof.store.domain.services.ICategoryService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCategoryCommandHandler implements ICommandHandler<CreateCategoryCommand> {

    private final ICategoryService categoryService;
    public CreateCategoryCommandHandler(ICategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @Override
    public void handle(CreateCategoryCommand command) {
        UUID id = categoryService.create(new CategoryDto(UUID.randomUUID(), command.getName(),command.getDescription()));
        command.setId(id);
    }
}
