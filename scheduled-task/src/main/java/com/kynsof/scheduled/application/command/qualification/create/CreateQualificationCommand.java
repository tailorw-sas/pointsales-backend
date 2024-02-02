package com.kynsof.scheduled.application.command.qualification.create;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommand;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateQualificationCommand implements ICommand {

    private UUID id;

    private String description;

    public CreateQualificationCommand(String description) {
        this.id = UUID.randomUUID();
        this.description = description;
    }

    public static CreateQualificationCommand fromRequest(CreateQualificationRequest request) {
        return new CreateQualificationCommand(request.getDescription());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateQualificationMessage(id);
    }
}
