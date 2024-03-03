package com.kynsof.store.application.command.deleted;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteSupplierCommand implements ICommand {
    private final UUID supplierId;

    public DeleteSupplierCommand(UUID supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
