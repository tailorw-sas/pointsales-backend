package com.kynsof.identity.application.command.permission.delete;

import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeletePermissionCommandHandler implements ICommandHandler<DeletePermissionCommand> {

    private final IPermissionService service;

    public DeletePermissionCommandHandler(IPermissionService service) {
        this.service = service;
    }

    @Override
    public void handle(DeletePermissionCommand command) {
        service.delete(command.getId());
    }

}
