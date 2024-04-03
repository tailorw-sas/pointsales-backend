package com.kynsof.identity.application.command.userrolbusiness.create.lote;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteUserRoleBusinessRequest {
    private UUID user;
    private List<UUID> roles;
    private UUID business;
}
