package com.kynsof.identity.application.command.role.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleRequest {
    private String name;
    private String description;
}
