package com.kynsof.identity.domain.dto.me;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRolesPermissionsDto {
    private UUID businessId;
    private String name;
    private List<RolePermissionsDto> roles;
    private Set<PermissionInfo> uniquePermissions;
}
