package com.kynsof.identity.application.command.role.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoleMessage implements ICommandMessage {

    private UUID id;
    private final String command = "UPDATE_ROLE";

    public UpdateRoleMessage(UUID id) {
        this.id = id;
    }

}
