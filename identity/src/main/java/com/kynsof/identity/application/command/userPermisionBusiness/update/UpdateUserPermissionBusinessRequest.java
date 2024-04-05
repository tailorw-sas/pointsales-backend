package com.kynsof.identity.application.command.userPermisionBusiness.update;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateUserPermissionBusinessRequest {

    private List<UserRoleBusinessUpdateRequest> payload;

}