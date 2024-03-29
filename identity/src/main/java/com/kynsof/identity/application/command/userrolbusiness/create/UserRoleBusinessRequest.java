package com.kynsof.identity.application.command.userrolbusiness.create;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleBusinessRequest {
    private UUID user;
    private UUID role;
    private UUID business;
}
