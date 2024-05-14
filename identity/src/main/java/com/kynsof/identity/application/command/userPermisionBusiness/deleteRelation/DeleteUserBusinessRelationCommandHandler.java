package com.kynsof.identity.application.command.userPermisionBusiness.deleteRelation;

import com.kynsof.identity.domain.dto.UserPermissionBusinessDto;
import com.kynsof.identity.domain.interfaces.service.IUserPermissionBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class DeleteUserBusinessRelationCommandHandler implements ICommandHandler<DeleteUserBusinessRelationCommand> {

    private final IUserPermissionBusinessService service;

    public DeleteUserBusinessRelationCommandHandler(IUserPermissionBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteUserBusinessRelationCommand command) {
        // Recuperar el estado actual
        List<UserPermissionBusinessDto> currentPermissions = service.findByUserAndBusiness(command.getUserId(),
                        command.getBusinessId())
                .stream()
                .toList();
        this.service.delete(currentPermissions);
    }
}