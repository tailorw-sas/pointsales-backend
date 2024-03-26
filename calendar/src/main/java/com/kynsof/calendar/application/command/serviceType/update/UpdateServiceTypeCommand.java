package com.kynsof.calendar.application.command.serviceType.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateServiceTypeCommand implements ICommand {

    private UUID id;

    private String name;

    public UpdateServiceTypeCommand(UUID id, String description) {
        this.id = id;
        this.name = description;
    }

    public static UpdateServiceTypeCommand fromRequest(UpdateServiceTypeRequest request) {
        return new UpdateServiceTypeCommand(request.getId(), request.getName());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateServiceTypeMessage(id);
    }
}
