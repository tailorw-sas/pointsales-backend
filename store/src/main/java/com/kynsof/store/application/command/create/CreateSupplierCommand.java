package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSupplierCommand implements ICommand {
    private String name;
    private String address;
    private String phone;
    private String email;

    public CreateSupplierCommand(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public ICommandMessage getMessage() {
        // Implementar lógica para obtener el mensaje de comando correspondiente
        return null;
    }
}
