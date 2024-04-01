package com.kynsof.identity.application.command.permission.update;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdatePermissionCommandHandler  implements ICommandHandler<UpdatePermissionCommand> {

    private final IPermissionService service;
    private final IModuleService serviceModule;

    public UpdatePermissionCommandHandler(IPermissionService service, IModuleService serviceModule) {
        this.service = service;
        this.serviceModule = serviceModule;
    }

    @Override
    public void handle(UpdatePermissionCommand command) {
       ModuleDto module = this.serviceModule.findById(command.getIdModule());
       service.update(new PermissionDto(command.getId(), command.getCode(), command.getDescription(), module, command.getStatus()));
    }
}