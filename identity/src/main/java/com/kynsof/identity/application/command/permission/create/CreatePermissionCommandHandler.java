package com.kynsof.identity.application.command.permission.create;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreatePermissionCommandHandler implements ICommandHandler<CreatePermissionCommand> {

    private final IPermissionService service;

    public CreatePermissionCommandHandler(IPermissionService service) {
        this.service = service;
    }

    @Override
    public void handle(CreatePermissionCommand command) {

        service.create(new PermissionDto(command.getId(), command.getCode(), command.getDescription()));
    }
}