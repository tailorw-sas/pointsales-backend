package com.kynsof.identity.application.command.rolpermission.update;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRolPermissionRequest {

    private UUID idRol;
    private List<UUID> permissions;

}
