package com.kynsof.store.application.command.subcategory.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.CategoryDto;
import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import com.kynsof.store.domain.services.ICategoryService;
import com.kynsof.store.domain.services.ISubcategoryService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateSubcategoryCommandHandler  implements ICommandHandler<CreateSubcategoryCommand>  {

  private final ISubcategoryService subcategoryService;
  private final ICategoryService categoryService;

    public CreateSubcategoryCommandHandler(ISubcategoryService test, ICategoryService categoryService) {
        this.subcategoryService = test;
        this.categoryService = categoryService;
    }

    @Override
    public void handle(CreateSubcategoryCommand command) {
        CategoryDto categoryDto = categoryService.findById(command.getCategoryId());
        UUID id = subcategoryService.create(new SubcategoryEntityDto(command.getId(),command.getName(),
                command.getDescription(),command.getCategoryId(), categoryDto));
        command.setId(id);
    }
}

