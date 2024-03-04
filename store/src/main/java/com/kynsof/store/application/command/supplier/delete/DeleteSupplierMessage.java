package com.kynsof.store.application.command.supplier.delete;


import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class DeleteSupplierMessage implements ICommandMessage {
    private final String command = "DELETE_SUPPLIER";

    public DeleteSupplierMessage() {
    }
}
