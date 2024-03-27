package com.kynsof.identity.application.command.business.update;

import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
import com.kynsof.share.core.application.FileRequest;
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
    private String latitude;
    private String longitude;
    private String description;
    private FileRequest logo;
    private String ruc;
    private EBusinessStatus status;

    public UpdateBusinessCommand(UUID id, String name, String latitude, String longitude, String description, FileRequest logo, String ruc, EBusinessStatus status) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.logo = logo;
        this.ruc = ruc;
        this.status = status;
    }

    public static UpdateBusinessCommand fromRequest(UpdateBusinessRequest request) {
        return new UpdateBusinessCommand(request.getId(), request.getName(), request.getLatitude(),
                request.getLongitude(), request.getDescription(), request.getLogo(), request.getRuc(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateBusinessMessage(id);
    }
}
