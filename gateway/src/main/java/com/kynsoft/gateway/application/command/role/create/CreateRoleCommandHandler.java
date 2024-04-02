package com.kynsoft.gateway.application.command.role.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.domain.dto.role.RoleRequest;
import com.kynsoft.gateway.domain.interfaces.IRoleService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateRoleCommandHandler implements ICommandHandler<CreateRoleCommand> {
    private final IRoleService roleService;

    public CreateRoleCommandHandler(IRoleService roleService) {

        this.roleService = roleService;
    }

    @Override
    public void handle(CreateRoleCommand command) {
        UUID id = this.roleService.createRole(new RoleRequest(command.getName(), command.getDescription()));
        command.setResul(id);
    }
}
