package com.kynsof.identity.application.command.role.update;

import com.kynsof.identity.domain.dto.RolDto;
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
        roleService.update(new RolDto(command.getId(),command.getName(),command.getDescription()));
    }
}
