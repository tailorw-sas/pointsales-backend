package com.kynsof.patients.application.command.insurance.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateNewInsuranceCommand implements ICommand {

    private UUID id;
    private String name;
    private String description;

    public CreateNewInsuranceCommand(String name, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }

    public static CreateNewInsuranceCommand fromRequest(CreateNewInsuranceRequest request) {
        return new CreateNewInsuranceCommand(request.getName(), request.getDescription());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateNewInsuranceMessage(id);
    }
}
