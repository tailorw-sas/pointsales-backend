package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateSupplierCommandHandler implements ICommandHandler<CreateSupplierCommand> {

//    private final ISupplierService supplierService;

    public CreateSupplierCommandHandler() {
    }

    @Override
    public void handle(CreateSupplierCommand command) {

    }
}
