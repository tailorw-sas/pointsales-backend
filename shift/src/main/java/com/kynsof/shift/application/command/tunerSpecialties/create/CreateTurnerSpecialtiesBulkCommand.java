package com.kynsof.shift.application.command.tunerSpecialties.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateTurnerSpecialtiesBulkCommand implements ICommand {

 private final List<CreateTurnerSpecialtiesCommand> commandList;

    public CreateTurnerSpecialtiesBulkCommand(List<CreateTurnerSpecialtiesCommand> commandList) {
        this.commandList = commandList;
    }
    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
