package com.kynsof.identity.application.command.businessmodule.update;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBusinessModuleRequest {
    private UUID idBusiness;
    private List<UUID> modules;
}
