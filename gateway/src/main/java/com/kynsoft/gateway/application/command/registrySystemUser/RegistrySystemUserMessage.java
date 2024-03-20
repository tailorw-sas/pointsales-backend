package com.kynsoft.gateway.application.command.registrySystemUser;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class RegistrySystemUserMessage implements ICommandMessage {

    private final Boolean result;
    private final String command = "REGISTRY_SYSTEM_USER";

    public RegistrySystemUserMessage(Boolean result) {
        this.result = result;
    }

}
