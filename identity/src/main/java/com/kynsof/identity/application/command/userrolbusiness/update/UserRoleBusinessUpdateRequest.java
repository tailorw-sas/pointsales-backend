package com.kynsof.identity.application.command.userrolbusiness.update;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleBusinessUpdateRequest {
    private UUID id;
    private UUID user;
    private UUID role;
    private UUID business;
}
