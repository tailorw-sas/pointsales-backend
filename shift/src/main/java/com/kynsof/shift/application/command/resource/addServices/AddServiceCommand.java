package com.kynsof.shift.application.command.resource.addServices;

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
    private final UUID businessId;
    private final String name;
    private String code;
    private final String image;
    private final List<UUID> serviceIds;

    public AddServiceCommand(UUID id, UUID businessId, String name, String code, String image, List<UUID> serviceIds) {
        this.id = id;
        this.businessId = businessId;
        this.name = name;
        this.code = code;
        this.image = image;
        this.serviceIds = serviceIds;
    }

    public static AddServiceCommand fromRequest(CreateResourceRequest request) {
        return new AddServiceCommand(request.getResourceId(), request.getBusinessId(), request.getName(), request.getCode(), request.getImage(), request.getServiceIds());
    }

    @Override
    public ICommandMessage getMessage() {
        return new AddServiceMessage(id);
    }
}
