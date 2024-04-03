package com.kynsof.identity.application.command.userrolbusiness.create.lote;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteCreateUserRoleBusinessRequest {

    private List<LoteUserRoleBusinessRequest> payload;

}