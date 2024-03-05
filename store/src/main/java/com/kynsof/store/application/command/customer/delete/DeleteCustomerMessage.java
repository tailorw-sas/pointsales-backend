package com.kynsof.store.application.command.customer.delete;


import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class DeleteCustomerMessage implements ICommandMessage {
    private final String command = "DELETE_CATEGORY";

    public DeleteCustomerMessage() {
    }
}
