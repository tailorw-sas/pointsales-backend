package com.kynsof.identity.application.command.permission.update;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdatePermissionCommandHandler  implements ICommandHandler<UpdatePermissionCommand> {

    private final IPermissionService service;

    public UpdatePermissionCommandHandler(IPermissionService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdatePermissionCommand command) {
       service.update(new PermissionDto(command.getId(), command.getCode(), command.getDescription(), command.getStatus()));
    }
}