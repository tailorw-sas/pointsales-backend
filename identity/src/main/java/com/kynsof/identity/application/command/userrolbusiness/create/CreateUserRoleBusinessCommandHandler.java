package com.kynsof.identity.application.command.userrolbusiness.create;

import com.kynsof.identity.domain.dto.*;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.identity.domain.interfaces.service.IUserRoleBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateUserRoleBusinessCommandHandler implements ICommandHandler<CreateUserRoleBusinessCommand> {

    private final IUserRoleBusinessService service;

    private final IPermissionService permissionService;

    private final IBusinessService businessService;

    private final IUserSystemService userSystemService;

    public CreateUserRoleBusinessCommandHandler(IUserRoleBusinessService service,
                                                IPermissionService permissionService,
                                             IBusinessService businessService,
                                             IUserSystemService userSystemService) {
        this.service = service;
        this.permissionService = permissionService;
        this.businessService = businessService;
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(CreateUserRoleBusinessCommand command) {
        List<UserRoleBusinessDto> userRoleBusinessDtos = new ArrayList<>();

        for (UserRoleBusinessRequest userRoleBusinessRequest : command.getPayload()) {
            UserSystemDto userSystemDto = this.userSystemService.findById(userRoleBusinessRequest.getUserId());
            PermissionDto roleDto = this.permissionService.findById(userRoleBusinessRequest.getPermissionId());
            BusinessDto businessDto = this.businessService.findById(userRoleBusinessRequest.getBusinessId());
            userRoleBusinessDtos.add(new UserRoleBusinessDto(UUID.randomUUID(), userSystemDto, roleDto, businessDto));
        }

        this.service.create(userRoleBusinessDtos);
    }
}