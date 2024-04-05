package com.kynsof.identity.application.command.userPermisionBusiness.create;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserPermissionBusinessRequest {

    private List<UserPermissionBusinessRequest> payload;

}