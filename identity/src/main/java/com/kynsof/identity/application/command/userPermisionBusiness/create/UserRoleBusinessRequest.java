package com.kynsof.identity.application.command.userPermisionBusiness.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRoleBusinessRequest {
    private UUID userId;
    private UUID permissionId;
    private UUID businessId;
}
