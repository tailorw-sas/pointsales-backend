package com.kynsof.store.application.command.subcategory.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateSubcategoryMessage implements ICommandMessage {
    private final String command = "UPDATE_SUBCATEGORY";

    public UpdateSubcategoryMessage() {
    }
}

