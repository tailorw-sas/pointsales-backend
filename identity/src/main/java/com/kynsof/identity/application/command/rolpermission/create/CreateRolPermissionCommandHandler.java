package com.kynsof.identity.application.command.rolpermission.create;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.identity.domain.interfaces.IRoleService;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.utils.ConfigureTimeZone;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateRolPermissionCommandHandler implements ICommandHandler<CreateRolPermissionCommand> {

    private final IRolePermissionService service;

    private final IRoleService roleService;

    private final IPermissionService permissionService;

    public CreateRolPermissionCommandHandler(IRolePermissionService service, 
                                             IRoleService roleService, 
                                             IPermissionService permissionService) {
        this.service = service;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Override
    public void handle(CreateRolPermissionCommand command) {
        RoleDto role = this.roleService.findById(command.getIdRol());
        List<RolePermissionDto> permissions = new ArrayList<>();
        
        for (UUID permission : command.getPermissions()) {
            PermissionDto p = this.permissionService.findById(permission);
            permissions.add(new RolePermissionDto(UUID.randomUUID(), role, p, ConfigureTimeZone.getTimeZone(), false));
        }
        this.service.create(permissions);
    }
}