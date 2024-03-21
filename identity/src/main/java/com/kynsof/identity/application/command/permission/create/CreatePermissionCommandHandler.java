package com.kynsof.identity.application.command.permission.create;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.interfaces.IRoleService;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreatePermissionCommandHandler implements ICommandHandler<CreatePermissionCommand> {

    private final IPermissionService service;
    private final IRoleService roleService;

    public CreatePermissionCommandHandler(IPermissionService service, IRoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @Override
    public void handle(CreatePermissionCommand command) {
        Set<RoleDto> roles = new HashSet<>();
        for (UUID role : command.getRoles()) {
            roles.add(this.roleService.findById(role));
        }
        service.create(new PermissionDto(command.getId(), command.getCode(), command.getDescription(), roles));
    }
}