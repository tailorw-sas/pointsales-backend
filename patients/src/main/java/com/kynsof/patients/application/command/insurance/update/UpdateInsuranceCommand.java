package com.kynsof.patients.application.command.insurance.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateInsuranceCommand implements ICommand {

    private UUID id;
    private String name;
    private String description;

    public UpdateInsuranceCommand(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static UpdateInsuranceCommand fromRequest(UpdateInsuranceRequest request, UUID id) {
        return new UpdateInsuranceCommand(id, request.getName(), request.getDescription());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateInsuranceMessage(id);
    }
}
