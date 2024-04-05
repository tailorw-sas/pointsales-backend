package com.kynsof.identity.application.command.businessModule.create;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessModuleRequest {
    private UUID idBusiness;
    private List<UUID> modules;
}
