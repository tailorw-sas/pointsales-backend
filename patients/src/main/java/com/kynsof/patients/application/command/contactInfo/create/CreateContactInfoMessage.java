package com.kynsof.patients.application.command.contactInfo.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateContactInfoMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_CONTACT_INFO";

    public CreateContactInfoMessage(UUID id) {
        this.id = id;
    }

}
