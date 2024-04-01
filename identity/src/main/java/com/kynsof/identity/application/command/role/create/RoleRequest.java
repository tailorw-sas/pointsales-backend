package com.kynsof.identity.application.command.role.create;

import com.kynsof.identity.domain.dto.enumType.RoleStatusEnm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    private String name;
    private String description;
    private RoleStatusEnm status;
}
