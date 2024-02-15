package com.kynsof.patients.application.command.additionalInfo.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatAdditionalInfoMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_ADDITIONAL_INFO";

    public CreatAdditionalInfoMessage(UUID id) {
        this.id = id;
    }

}
