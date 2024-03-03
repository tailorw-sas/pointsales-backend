package com.kynsof.store.application.command.deleted;



import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategoryCommandHandler implements ICommandHandler<DeleteCategoryCommand> {

//   private final ICategoryService categoryService;

    public DeleteCategoryCommandHandler() {
    }

    @Override
    public void handle(DeleteCategoryCommand command) {
//        categoryService.deleteCategory(command.getCategoryId());
    }
}
