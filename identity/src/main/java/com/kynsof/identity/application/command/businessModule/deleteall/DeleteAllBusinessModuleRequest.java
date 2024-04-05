package com.kynsof.identity.application.command.businessModule.deleteall;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteAllBusinessModuleRequest {
    private List<UUID> businessModules;
}
