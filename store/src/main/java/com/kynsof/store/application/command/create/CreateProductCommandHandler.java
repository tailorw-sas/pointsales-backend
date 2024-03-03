package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateProductCommandHandler implements ICommandHandler<CreateProductCommand> {


    public CreateProductCommandHandler() {
    }

    @Override
    public void handle(CreateProductCommand command) {

    }
}
