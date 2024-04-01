package com.kynsof.identity.application.command.permission.update;

import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePermissionCommand implements ICommand {

    private UUID id;
    private String code;
    private String description;
    private UUID idModule;
    private PermissionStatusEnm status;

    public UpdatePermissionCommand(UUID id, String code, String description, UUID idModule, PermissionStatusEnm status) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.idModule = idModule;
        this.status = status;
    }

    public static UpdatePermissionCommand fromRequest(UpdatePermissionRequest request) {
        return new UpdatePermissionCommand(request.getId(), request.getCode(), request.getDescription(), request.getModuleId(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdatePermissionMessage(id);
    }
}
