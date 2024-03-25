package com.kynsof.identity.domain.dto.me;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionsDto {
    private UUID roleId;
    private String roleName;
    private List<PermissionInfo> permissions;
}
