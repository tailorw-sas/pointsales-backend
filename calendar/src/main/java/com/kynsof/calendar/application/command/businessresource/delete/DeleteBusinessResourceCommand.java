package com.kynsof.calendar.application.command.businessresource.delete;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteBusinessResourceCommand implements ICommand {

    private UUID business;
    private UUID resource;

    public DeleteBusinessResourceCommand(UUID business, UUID resource) {
        this.business = business;
        this.resource = resource;
    }

    public static DeleteBusinessResourceCommand fromRequest(DeleteBusinessResourceRequest request) {
        return new DeleteBusinessResourceCommand(request.getBusiness(), request.getResource());
    }

    @Override
    public ICommandMessage getMessage() {
        return new DeleteBusinessResourceMessage(resource);
    }
}
