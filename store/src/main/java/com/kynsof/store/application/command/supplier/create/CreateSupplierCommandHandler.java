package com.kynsof.store.application.command.supplier.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateSupplierCommandHandler implements ICommandHandler<CreateSupplierCommand> {

    private final ISupplierService supplierService;

    public CreateSupplierCommandHandler(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void handle(CreateSupplierCommand command) {
        SupplierEntityDto supplierEntityDto = new SupplierEntityDto(command.getId(), command.getName(), command.getAddress(),
                 command.getPhone(),command.getEmail());
        UUID id = supplierService.create(supplierEntityDto);
        command.setId(id);
    }
}
