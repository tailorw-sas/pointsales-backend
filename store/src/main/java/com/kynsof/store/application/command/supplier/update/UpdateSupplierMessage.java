package com.kynsof.store.application.command.supplier.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateSupplierMessage implements ICommandMessage {
    private final String command = "UPDATE_SUPPLIER";

    public UpdateSupplierMessage() {
    }
}
