package com.kynsof.patients.application.command.contactInfo.delete;



import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteContactMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PATIENT";

    public DeleteContactMessage(UUID id) {
        this.id = id;
    }

}
