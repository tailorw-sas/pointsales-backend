package com.kynsof.calendar.application.command.businessresource.update;

import com.kynsof.calendar.application.command.businessresource.create.*;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessResourceCommand implements ICommand {

    private UUID id;
    private UUID business;
    private UUID resource;

    public UpdateBusinessResourceCommand(UUID id, UUID business, UUID resource) {
        this.id = id;
        this.business = business;
        this.resource = resource;
    }

    public static UpdateBusinessResourceCommand fromRequest(UpdateBusinessresourceRequest request, UUID id) {
        return new UpdateBusinessResourceCommand(id, request.getBusiness(), request.getResource());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateBusinessResourceMessage(id);
    }
}
