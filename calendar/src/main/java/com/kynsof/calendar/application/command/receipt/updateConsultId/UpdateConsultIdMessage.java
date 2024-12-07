package com.kynsof.calendar.application.command.receipt.updateConsultId;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateConsultIdMessage implements ICommandMessage {

    private final Boolean id;

    private final String command = "UPDATE_RECEIPT";

    public UpdateConsultIdMessage(Boolean id) {
        this.id = id;
    }

}
