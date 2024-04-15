package com.kynsof.calendar.application.command.businessresource.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBusinessResourceCommand implements ICommand {

    private UUID id;
    private UUID business;
    private UUID resource;

    public CreateBusinessResourceCommand(UUID business, UUID resource) {
        this.id = UUID.randomUUID();
        this.business = business;
        this.resource = resource;
    }

    public static CreateBusinessResourceCommand fromRequest(CreateBusinessresourceRequest request) {
        return new CreateBusinessResourceCommand(request.getBusiness(), request.getResource());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateBusinessResourceMessage(id);
    }
}
