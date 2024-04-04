package com.kynsof.identity.application.command.userPermisionBusiness.update;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRoleBusinessRequest {

    private List<UserRoleBusinessUpdateRequest> payload;

}