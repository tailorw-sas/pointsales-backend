package com.kynsof.identity.application.command.userPermisionBusiness.create.lote;

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
public class LoteCreateUserRoleBusinessCommandHandler implements ICommandHandler<LoteCreateUserRoleBusinessCommand> {

    private final IUserRoleBusinessService service;

    private final IPermissionService permissionService;

    private final IBusinessService businessService;

    private final IUserSystemService userSystemService;

    public LoteCreateUserRoleBusinessCommandHandler(IUserRoleBusinessService service, 
                                             IPermissionService permissionService,
                                             IBusinessService businessService,
                                             IUserSystemService userSystemService) {
        this.service = service;
        this.permissionService = permissionService;
        this.businessService = businessService;
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(LoteCreateUserRoleBusinessCommand command) {
        List<UserRoleBusinessDto> userRoleBusinessDtos = new ArrayList<>();

        for (LoteUserPermissionBusinessRequest userRoleBusinessRequest : command.getPayload()) {
            UserSystemDto userSystemDto = this.userSystemService.findById(userRoleBusinessRequest.getUserId());
            BusinessDto businessDto = this.businessService.findById(userRoleBusinessRequest.getBusinessId());

            for (UUID role : userRoleBusinessRequest.getPermissionIds()) {
                PermissionDto roleDto = this.permissionService.findById(role);
                userRoleBusinessDtos.add(new UserRoleBusinessDto(UUID.randomUUID(), userSystemDto, roleDto, businessDto));
            }
        }

        this.service.create(userRoleBusinessDtos);
    }
}