package com.kynsof.store.application.command.inventory.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateInventoryMovementMessage implements ICommandMessage {
    private final UUID id;
    private final String command = "CREATE_INVENTORY";

    public CreateInventoryMovementMessage(UUID id) {
        this.id = id;
    }
}
