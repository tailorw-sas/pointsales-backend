package com.kynsof.identity.application.command.role.update;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.interfaces.IRoleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoleCommandHandler implements ICommandHandler<UpdateRoleCommand> {

    private final IRoleService roleService;

    public UpdateRoleCommandHandler(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void handle(UpdateRoleCommand command) {
        roleService.update(new RoleDto(command.getId(),command.getName(),command.getDescription(), command.getStatus()));
    }
}
