package com.kynsof.patients.application.command.contactInfo.create;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ContactInfoContactMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_CONTACT_INFO";

    public ContactInfoContactMessage(UUID id) {
        this.id = id;
    }

}
