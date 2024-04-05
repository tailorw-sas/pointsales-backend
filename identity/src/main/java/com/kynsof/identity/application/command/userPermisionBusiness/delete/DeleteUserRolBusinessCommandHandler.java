package com.kynsof.identity.application.command.userPermisionBusiness.delete;

import com.kynsof.identity.domain.interfaces.service.IUserPermissionBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserRolBusinessCommandHandler implements ICommandHandler<DeleteUserRolBusinessCommand> {

    private final IUserPermissionBusinessService serviceImpl;

    public DeleteUserRolBusinessCommandHandler(IUserPermissionBusinessService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteUserRolBusinessCommand command) {

        serviceImpl.delete(command.getId());
    }

}
