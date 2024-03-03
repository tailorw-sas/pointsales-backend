package com.kynsof.store.application.command.deleted;



import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteProductCommand implements ICommand {
    private final UUID productId;

    public DeleteProductCommand(UUID productId) {
        this.productId = productId;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
