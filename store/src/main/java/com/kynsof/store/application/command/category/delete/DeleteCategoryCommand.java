package com.kynsof.store.application.command.category.delete;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteCategoryCommand implements ICommand {
    private final UUID categoryId;

    public DeleteCategoryCommand(UUID categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
