package com.kynsof.store.application.command.update;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateCategoryCommandHandler implements ICommandHandler<UpdateCategoryCommand> {

//    private final ICategoryService categoryService;

    public UpdateCategoryCommandHandler() {

    }

    @Override
    public void handle(UpdateCategoryCommand command) {

    }
}

