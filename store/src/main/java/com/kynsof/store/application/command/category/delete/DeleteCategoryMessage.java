package com.kynsof.store.application.command.category.delete;


import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class DeleteCategoryMessage implements ICommandMessage {
    private final String command = "DELETE_CATEGORY";

    public DeleteCategoryMessage() {
    }
}
