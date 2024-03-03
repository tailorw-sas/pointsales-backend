package com.kynsof.store.application.command.update;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.request.SupplierRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateSupplierCommand implements ICommand {
    private final UUID supplierId;
    private final String name;
    private final String address;
    private final String phone;
    private final String email;

    public UpdateSupplierCommand(UUID supplierId, String name, String address, String phone, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public static UpdateSupplierCommand fromRequest(UUID supplierId, SupplierRequest request) {
        return new UpdateSupplierCommand(
                supplierId,
                request.getName(),
                request.getAddress(),
                request.getPhone(),
                request.getEmail()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        // Implementación específica
        return null;
    }
}
