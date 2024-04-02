package com.kynsof.identity.application.command.role.create;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.enumType.RoleStatusEnm;
import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class CreateRoleCommandHandler implements ICommandHandler<CreateRoleCommand> {

    private final IRoleService roleService;

    public CreateRoleCommandHandler(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void handle(CreateRoleCommand command) {
      UUID id=  roleService.create(new RoleDto(UUID.randomUUID(), command.getName(), command.getDescription(),
              RoleStatusEnm.ACTIVE, new ArrayList<>()));
      command.setId(id);
    }
}
