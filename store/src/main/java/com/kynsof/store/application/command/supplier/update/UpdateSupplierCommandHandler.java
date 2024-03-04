package com.kynsof.store.application.command.supplier.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.stereotype.Component;

@Component
public class UpdateSupplierCommandHandler implements ICommandHandler<UpdateSupplierCommand> {

    private final ISupplierService supplierService;

    public UpdateSupplierCommandHandler(ISupplierService supplierService) {

        this.supplierService = supplierService;
    }

    @Override
    public void handle(UpdateSupplierCommand command) {
        SupplierEntityDto supplierEntityDto = new SupplierEntityDto(command.getSupplierId(), command.getName(), command.getAddress(),
                command.getPhone(), command.getEmail());
        supplierService.update(supplierEntityDto);
    }
}
