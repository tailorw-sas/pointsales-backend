package com.kynsof.identity.application.command.userPermisionBusiness.create.lote;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoteCreateUserRoleBusinessRequest {

    private List<LoteUserPermissionBusinessRequest> payload;

}