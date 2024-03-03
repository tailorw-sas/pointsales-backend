package com.kynsof.store.application.command.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateSubcategoryCommandHandler implements ICommandHandler<UpdateSubcategoryCommand> {

//    private final ISubcategoryService subcategoryService;

    public UpdateSubcategoryCommandHandler() {
    }

    @Override
    public void handle(UpdateSubcategoryCommand command) {
//        SubcategoryDto subcategoryDto = new SubcategoryDto(command.getSubcategoryId(), command.getName(), command.getDescription(), command.getCategoryId());
//        subcategoryService.updateSubcategory(subcategoryDto);
    }
}
