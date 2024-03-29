package com.kynsof.identity.application.command.rolpermission.update;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UpdateRolPermissionCommandHandler implements ICommandHandler<UpdateRolPermissionCommand> {

    private final IRolePermissionService service;

    private final IRoleService roleService;

    private final IPermissionService permissionService;

    public UpdateRolPermissionCommandHandler(IRolePermissionService service,
            IRoleService roleService,
            IPermissionService permissionService) {
        this.service = service;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Override
    public void handle(UpdateRolPermissionCommand command) {
        List<RolePermissionDto> permissionsForRol = this.roleService.findPermissionForRoleById(command.getIdRol());
        List<RolePermissionDto> removePermissionsForRol = new ArrayList<>();

        //Identificar permisos para eliminar
        for (RolePermissionDto rolePermissionDto : permissionsForRol) {
            boolean result = false;
            for (UUID permission : command.getPermissions()) {
                if (rolePermissionDto.getPermission().getId().equals(permission)) {
                    result = true;
                }
            }
            if (!result) {
                removePermissionsForRol.add(rolePermissionDto);
            }
        }
        this.service.delete(removePermissionsForRol);

        // Identificar permisos para agregar
        //RoleDto role = this.roleService.findById(command.getIdRol());
        RoleDto role = permissionsForRol.get(0).getRole();
        List<RolePermissionDto> addPermissionsRol = new ArrayList<>();
        for (UUID permission : command.getPermissions()) {
            boolean result = false;
            for (RolePermissionDto rolePermissionDto : permissionsForRol) {
                if (rolePermissionDto.getPermission().getId().equals(permission)) {
                    result = true;
                }
            }
            if (!result) {
                PermissionDto p = this.permissionService.findById(permission);
                addPermissionsRol.add(new RolePermissionDto(UUID.randomUUID(), role, p, ConfigureTimeZone.getTimeZone(), false));
            }
        }
        this.service.create(addPermissionsRol);
    }
}
