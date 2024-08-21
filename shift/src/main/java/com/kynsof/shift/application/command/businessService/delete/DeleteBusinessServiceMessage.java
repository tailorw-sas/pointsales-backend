package com.kynsof.shift.application.command.businessService.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteBusinessServiceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_BUSINESS_MODULE";

    public DeleteBusinessServiceMessage(UUID id) {
        this.id = id;
    }

}
