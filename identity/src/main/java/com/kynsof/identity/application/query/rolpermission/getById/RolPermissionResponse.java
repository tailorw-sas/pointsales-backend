package com.kynsof.identity.application.query.rolpermission.getById;

import com.kynsof.identity.application.query.permission.getById.PermissionResponse;
import com.kynsof.identity.application.query.permission.getById.RoleResponse;
import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolPermissionResponse implements IResponse {
    private UUID id;
    private RoleResponse role;
    private PermissionResponse permission;
    private LocalDateTime deletedAt;
    private boolean deleted;

    public RolPermissionResponse(RolePermissionDto rolePermissionDto) {
        this.id = rolePermissionDto.getId();
        this.role = new RoleResponse(rolePermissionDto.getRole());
        this.permission = new PermissionResponse(rolePermissionDto.getPermission());
        this.deletedAt = rolePermissionDto.getDeletedAt();
        this.deleted = rolePermissionDto.isDeleted();
    }

}
