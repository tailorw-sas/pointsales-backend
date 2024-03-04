package com.kynsof.store.application.command.category.update;


import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateCategoryMessage implements ICommandMessage {
    private final String command = "UPDATE_CATEGORY";

    public UpdateCategoryMessage() {
    }
}
