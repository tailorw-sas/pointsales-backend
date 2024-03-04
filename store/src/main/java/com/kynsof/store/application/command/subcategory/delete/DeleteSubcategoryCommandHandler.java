package com.kynsof.store.application.command.subcategory.delete;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.ISubcategoryService;
import org.springframework.stereotype.Component;

@Component
public class DeleteSubcategoryCommandHandler implements ICommandHandler<DeleteSubcategoryCommand> {

    private final ISubcategoryService subcategoryService;

    public DeleteSubcategoryCommandHandler(ISubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @Override
    public void handle(DeleteSubcategoryCommand command) {
        subcategoryService.delete(command.getSubcategoryId());
    }
}

