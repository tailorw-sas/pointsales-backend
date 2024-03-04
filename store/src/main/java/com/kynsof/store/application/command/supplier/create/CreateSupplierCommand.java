package com.kynsof.store.application.command.supplier.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateSupplierCommand implements ICommand {
    private String name;
    private String address;
    private String phone;
    private String email;
    private UUID id;

    public CreateSupplierCommand(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public static CreateSupplierCommand fromFrontRequest(SupplierRequest request) {
        return new CreateSupplierCommand(
                request.getName(),
                request.getAddress(),
                request.getPhone(),
                request.getEmail()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateSupplierMessage(id);
    }
}
