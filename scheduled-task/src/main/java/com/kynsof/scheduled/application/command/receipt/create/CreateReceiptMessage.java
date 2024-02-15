package com.kynsof.scheduled.application.command.receipt.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateReceiptMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_RECEIPT";

    public CreateReceiptMessage(UUID id) {
        this.id = id;
    }

}
