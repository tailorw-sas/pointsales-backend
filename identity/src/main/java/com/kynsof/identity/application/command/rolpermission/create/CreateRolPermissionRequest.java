package com.kynsof.identity.application.command.rolpermission.create;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRolPermissionRequest {

    private UUID idRol;
    private List<UUID> permissions;

}
