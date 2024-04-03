package com.kynsof.identity.application.command.userPermisionBusiness.create.lote;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class LoteUserPermissionBusinessRequest {
    private UUID userId;
    private List<UUID> permissionIds;
    private UUID businessId;
}
