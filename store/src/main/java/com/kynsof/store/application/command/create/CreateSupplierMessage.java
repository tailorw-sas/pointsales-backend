package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateSupplierMessage implements ICommandMessage {
    private final UUID id;
    private final String command = "CREATE_SUPPLIER";

    public CreateSupplierMessage(UUID id) {
        this.id = id;
    }
}

