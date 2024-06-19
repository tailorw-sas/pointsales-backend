package com.kynsof.calendar.application.command.resource.addServices;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AddServiceCommand implements ICommand {

    private final UUID id;
    private final String name;
    private final String image;
    private final List<UUID> serviceIds;

    public AddServiceCommand(UUID id, String name, String image, List<UUID> serviceIds) {


        this.id = id;
        this.name = name;
        this.image = image;
        this.serviceIds = serviceIds;
    }

    public static AddServiceCommand fromRequest(AddServiceRequest request) {
        return new AddServiceCommand(request.getResourceId(), request.getName(), request.getImage(), request.getServiceIds());
    }

    @Override
    public ICommandMessage getMessage() {
        return new AddServiceMessage(id);
    }
}
