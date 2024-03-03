package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateSubcategoryCommandHandler implements ICommandHandler<CreateSubcategoryCommand> {

//    private final ISubcategoryService subcategoryService;

    public CreateSubcategoryCommandHandler() {
    }

    @Override
    public void handle(CreateSubcategoryCommand command) {

    }
}

