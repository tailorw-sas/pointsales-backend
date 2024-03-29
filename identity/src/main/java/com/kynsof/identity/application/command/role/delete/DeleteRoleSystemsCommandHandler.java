package com.kynsof.identity.application.command.role.delete;


import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteRoleSystemsCommandHandler implements ICommandHandler<DeleteRoleSystemsCommand> {

    private final IRoleService serviceImpl;

    public DeleteRoleSystemsCommandHandler(IRoleService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteRoleSystemsCommand command) {

        serviceImpl.delete(command.getId());
    }

}
