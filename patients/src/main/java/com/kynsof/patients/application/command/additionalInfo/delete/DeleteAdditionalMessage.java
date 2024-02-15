package com.kynsof.patients.application.command.additionalInfo.delete;



import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteAdditionalMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PATIENT";

    public DeleteAdditionalMessage(UUID id) {
        this.id = id;
    }

}
