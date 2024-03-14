package com.kynsof.identity.application.command.role.delete;



import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteRoleSystemsMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_ROLE";

    public DeleteRoleSystemsMessage(UUID id) {
        this.id = id;
    }

}
