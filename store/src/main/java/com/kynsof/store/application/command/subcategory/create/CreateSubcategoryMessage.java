package com.kynsof.store.application.command.subcategory.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateSubcategoryMessage implements ICommandMessage {
    private final UUID id;
    private final String command = "CREATE_SUBCATEGORY";

    public CreateSubcategoryMessage(UUID id) {
        this.id = id;
    }
}
