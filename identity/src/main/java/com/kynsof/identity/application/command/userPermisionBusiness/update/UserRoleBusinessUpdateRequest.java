package com.kynsof.identity.application.command.userPermisionBusiness.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRoleBusinessUpdateRequest {
    private UUID id;
    private UUID userId;
    private UUID permissionId;
    private UUID businessId;
}
