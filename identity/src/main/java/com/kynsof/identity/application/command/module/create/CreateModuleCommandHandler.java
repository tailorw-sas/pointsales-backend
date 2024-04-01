package com.kynsof.identity.application.command.module.create;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateModuleCommandHandler implements ICommandHandler<CreateModuleCommand> {

    private final IModuleService service;

    public CreateModuleCommandHandler(IModuleService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateModuleCommand command) {

        service.create(new ModuleDto(command.getId(), command.getName(), command.getImage(), command.getDescription()));
    }
}