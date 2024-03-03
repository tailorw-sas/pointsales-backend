package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryCommandHandler implements ICommandHandler<CreateCategoryCommand> {

    public CreateCategoryCommandHandler() {

    }

    @Override
    public void handle(CreateCategoryCommand command) {

    }
}
