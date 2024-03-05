package com.kynsof.store.application.command.customer.delete;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteCustomerCommand implements ICommand {
    private final UUID categoryId;

    public DeleteCustomerCommand(UUID categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
