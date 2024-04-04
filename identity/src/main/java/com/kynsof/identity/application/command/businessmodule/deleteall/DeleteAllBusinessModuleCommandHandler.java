package com.kynsof.identity.application.command.businessmodule.deleteall;

import com.kynsof.identity.domain.interfaces.service.IBusinessModuleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteAllBusinessModuleCommandHandler implements ICommandHandler<DeleteAllBusinessModuleCommand> {

    private final IBusinessModuleService service;

    public DeleteAllBusinessModuleCommandHandler(IBusinessModuleService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteAllBusinessModuleCommand command) {
        service.delete(command.getBusinessModules());
    }

}
