package com.kynsof.identity.application.command.role.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateRoleMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_ROLE";

    public CreateRoleMessage(UUID id) {
        this.id = id;
    }

}
