package com.kynsof.identity.application.command.userPermisionBusiness.create;

import com.kynsof.identity.domain.dto.*;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.identity.domain.interfaces.service.IUserPermissionBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateUserPermissionBusinessCommandHandler implements ICommandHandler<CreateUserPermissionBusinessCommand> {

    private final IUserPermissionBusinessService service;

    private final IPermissionService permissionService;

    private final IBusinessService businessService;

    private final IUserSystemService userSystemService;

    public CreateUserPermissionBusinessCommandHandler(IUserPermissionBusinessService service,
                                                      IPermissionService permissionService,
                                                      IBusinessService businessService,
                                                      IUserSystemService userSystemService) {
        this.service = service;
        this.permissionService = permissionService;
        this.businessService = businessService;
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(CreateUserPermissionBusinessCommand command) {
        List<UserPermissionBusinessDto> userRoleBusinessDtos = new ArrayList<>();

        for (UserPermissionBusinessRequest userRoleBusinessRequest : command.getPayload()) {
            UserSystemDto userSystemDto = this.userSystemService.findById(userRoleBusinessRequest.getUserId());
            BusinessDto businessDto = this.businessService.findById(userRoleBusinessRequest.getBusinessId());

            for (UUID role : userRoleBusinessRequest.getPermissionIds()) {
                PermissionDto roleDto = this.permissionService.findById(role);
                userRoleBusinessDtos.add(new UserPermissionBusinessDto(UUID.randomUUID(), userSystemDto, roleDto, businessDto));
            }
        }

        this.service.create(userRoleBusinessDtos);
    }
}