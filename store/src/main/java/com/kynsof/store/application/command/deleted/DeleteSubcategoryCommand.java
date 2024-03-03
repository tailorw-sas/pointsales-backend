package com.kynsof.store.application.command.deleted;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteSubcategoryCommand implements ICommand {
    private final UUID subcategoryId;

    public DeleteSubcategoryCommand(UUID subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
