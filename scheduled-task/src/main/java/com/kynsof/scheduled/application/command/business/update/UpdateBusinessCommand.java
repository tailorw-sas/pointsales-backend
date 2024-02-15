package com.kynsof.scheduled.application.command.business.update;

import com.kynsof.scheduled.domain.dto.EBusinessStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessCommand implements ICommand {

    private UUID id;
    private String name;
    private String description;
    private byte[] logo;
    private String ruc;
    private EBusinessStatus status;

    public UpdateBusinessCommand(UUID id, String name, String description, byte[] logo, String ruc, EBusinessStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.ruc = ruc;
        this.status = status;
    }

    public static UpdateBusinessCommand fromRequest(UpdateBusinessRequest request) {
        return new UpdateBusinessCommand(request.getId(), request.getName(), request.getDescription(), request.getLogo(), request.getRuc(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateBusinessMessage(id);
    }
}
