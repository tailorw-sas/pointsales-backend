package com.kynsof.store.application.command.supplier.delete;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.stereotype.Component;

@Component
public class DeleteSupplierCommandHandler implements ICommandHandler<DeleteSupplierCommand> {


    private final ISupplierService supplierService;

    public DeleteSupplierCommandHandler(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void handle(DeleteSupplierCommand command) {
        supplierService.delete(command.getSupplierId());
    }
}
