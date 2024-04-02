package com.kynsoft.gateway.application.command.role.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateRoleCommand implements ICommand {
    private UUID resul;
    private final String name;
    private final String description;

    public CreateRoleCommand(String name, String description) {

        this.name = name;
        this.description = description;
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateRoleMessage(resul);
    }
}
