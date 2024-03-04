package com.kynsof.store.application.command.category.command.delete;



import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.ICategoryService;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategoryCommandHandler implements ICommandHandler<DeleteCategoryCommand> {

  private final ICategoryService categoryService;

    public DeleteCategoryCommandHandler(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void handle(DeleteCategoryCommand command) {
        categoryService.delete(command.getCategoryId());
    }
}
