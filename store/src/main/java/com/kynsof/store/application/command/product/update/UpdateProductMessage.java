package com.kynsof.store.application.command.product.update;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateProductMessage implements ICommandMessage {
    private final String command = "UPDATE_PRODUCT";

    public UpdateProductMessage() {
    }
}
