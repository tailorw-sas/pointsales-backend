package com.kynsof.patients.application.command.contactInfo.update;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateContactInfoMessage implements ICommandMessage {


    private final String command = "UPDATE_PATIENT";

    public UpdateContactInfoMessage() {

    }

}
