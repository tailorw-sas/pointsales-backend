package com.kynsof.store.application.command.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateSupplierCommandHandler implements ICommandHandler<UpdateSupplierCommand> {

//    private final ISupplierService supplierService;

    public UpdateSupplierCommandHandler() {

    }

    @Override
    public void handle(UpdateSupplierCommand command) {
//        SupplierDto supplierDto = new SupplierDto(command.getSupplierId(), command.getName(), command.getAddress(), command.getPhone(), command.getEmail());
//        supplierService.updateSupplier(supplierDto);
    }
}
