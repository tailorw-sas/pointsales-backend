package com.kynsof.store.application.command.deleted;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.ISubcategoryService;
import org.springframework.stereotype.Component;

@Component
public class DeleteSubcategoryCommandHandler implements ICommandHandler<DeleteSubcategoryCommand> {

    private ISubcategoryService subcategoryService;

//    public DeleteSubcategoryCommandHandler(ISubcategoryService subcategoryService) {
//        this.subcategoryService = subcategoryService;
//    }

    @Override
    public void handle(DeleteSubcategoryCommand command) {
        //subcategoryService.deleteSubcategory(command.getSubcategoryId());
    }
}

