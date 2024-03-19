package com.kynsoft.gateway.application.command.registry;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class RegistryMessage implements ICommandMessage {

    private final Boolean result;
    private final String command = "REGISTRY";

    public RegistryMessage(Boolean result) {
        this.result = result;
    }

}
