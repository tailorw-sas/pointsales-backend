package com.kynsof.identity.application.command.businessModule.update;

import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBusinessModuleRequest {
    private UUID idBusiness;
    private Set<UUID> modules;
}
