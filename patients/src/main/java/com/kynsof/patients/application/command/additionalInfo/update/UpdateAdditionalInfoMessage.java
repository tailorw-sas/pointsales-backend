package com.kynsof.patients.application.command.additionalInfo.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateAdditionalInfoMessage implements ICommandMessage {


        private final String command = "UPDATE_ADDITIONAL_INFO";

    public UpdateAdditionalInfoMessage() {

    }

}
