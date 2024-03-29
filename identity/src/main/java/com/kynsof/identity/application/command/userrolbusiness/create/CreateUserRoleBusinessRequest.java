package com.kynsof.identity.application.command.userrolbusiness.create;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRoleBusinessRequest {

    private List<UserRoleBusinessRequest> payload;

}