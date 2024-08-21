package com.kynsof.shift.application.command.resource.update;

import com.kynsof.shift.domain.dto.enumType.EResourceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateResourceCommand implements ICommand {

    private final UUID id;
    private final String name;
    private final String image;
    private final EResourceStatus status;
    private final List<UUID> serviceIds;

    public UpdateResourceCommand(UUID id,  String name, String image, EResourceStatus status, List<UUID> serviceIds) {

        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.serviceIds = serviceIds;
    }

    public static UpdateResourceCommand fromRequest(UUID id, UpdateResourceRequest request) {
        return new UpdateResourceCommand(id,  request.getName(), request.getImage(),
                request.getStatus(), request.getServiceIds());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateResourceMessage(id);
    }
}
