package com.kynsof.identity.domain.dto;

import com.kynsof.identity.infrastructure.identity.Permission;
import com.kynsof.identity.infrastructure.identity.RoleSystem;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionDto {

    private Long id;
    private RoleSystem role;
    private Permission permission;
    private LocalDateTime deletedAt;
}
