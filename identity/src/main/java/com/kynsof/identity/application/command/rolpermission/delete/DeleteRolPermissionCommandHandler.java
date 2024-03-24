package com.kynsof.identity.application.command.rolpermission.delete;

import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteRolPermissionCommandHandler implements ICommandHandler<DeleteRolPermissionCommand> {

    private final IRolePermissionService service;

    public DeleteRolPermissionCommandHandler(IRolePermissionService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteRolPermissionCommand command) {
        service.delete(command.getId());
    }

}
