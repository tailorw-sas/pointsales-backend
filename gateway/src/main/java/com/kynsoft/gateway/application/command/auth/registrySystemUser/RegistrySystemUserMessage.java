package com.kynsoft.gateway.application.command.auth.registrySystemUser;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class RegistrySystemUserMessage implements ICommandMessage {

    private final String result;
    private final String command = "REGISTRY_SYSTEM_USER";

    public RegistrySystemUserMessage(String result) {
        this.result = result;
    }

}
