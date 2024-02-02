package com.kynsof.patients.application.command.additionalInfo.update;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateAdditionalInfoMessage implements ICommandMessage {


    private final String command = "UPDATE_PATIENT";

    public UpdateAdditionalInfoMessage() {

    }

}
