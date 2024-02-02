package com.kynsof.scheduled.application.command.qualification.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommand;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QualificationDeleteCommand implements ICommand {

    private UUID id;

    @Override
    public ICommandMessage getMessage() {
        return new QualificationDeleteMessage(id);
    }

}
