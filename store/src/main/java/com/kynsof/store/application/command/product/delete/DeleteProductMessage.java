package com.kynsof.store.application.command.product.delete;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class DeleteProductMessage implements ICommandMessage {
    private final String command = "DELETE_PRODUCT";

    public DeleteProductMessage() {
    }
}
