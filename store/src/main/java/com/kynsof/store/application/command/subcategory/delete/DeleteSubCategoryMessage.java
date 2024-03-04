package com.kynsof.store.application.command.subcategory.delete;


import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class DeleteSubCategoryMessage implements ICommandMessage {
    private final String command = "DELETE_SUBCATEGORY";

    public DeleteSubCategoryMessage() {
    }
}
