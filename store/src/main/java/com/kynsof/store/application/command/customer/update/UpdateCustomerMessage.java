package com.kynsof.store.application.command.customer.update;


import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateCustomerMessage implements ICommandMessage {
    private final String command = "UPDATE_CATEGORY";

    public UpdateCustomerMessage() {
    }
}
