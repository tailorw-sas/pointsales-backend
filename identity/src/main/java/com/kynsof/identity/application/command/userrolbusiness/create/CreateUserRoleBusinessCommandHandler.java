package com.kynsof.identity.application.command.userrolbusiness.create;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.UserRoleBusinessDto;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.identity.domain.interfaces.service.IUserRoleBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateUserRoleBusinessCommandHandler implements ICommandHandler<CreateUserRoleBusinessCommand> {

    private final IUserRoleBusinessService service;

    private final IRoleService roleService;

    private final IBusinessService businessService;

    private final IUserSystemService userSystemService;

    public CreateUserRoleBusinessCommandHandler(IUserRoleBusinessService service, 
                                             IRoleService roleService, 
                                             IBusinessService businessService,
                                             IUserSystemService userSystemService) {
        this.service = service;
        this.roleService = roleService;
        this.businessService = businessService;
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(CreateUserRoleBusinessCommand command) {
        List<UserRoleBusinessDto> userRoleBusinessDtos = new ArrayList<>();

        for (UserRoleBusinessRequest userRoleBusinessRequest : command.getPayload()) {
            UserSystemDto userSystemDto = this.userSystemService.findById(userRoleBusinessRequest.getUser());
            RoleDto roleDto = this.roleService.findById(userRoleBusinessRequest.getRole());
            BusinessDto businessDto = this.businessService.findById(userRoleBusinessRequest.getBusiness());
            userRoleBusinessDtos.add(new UserRoleBusinessDto(UUID.randomUUID(), userSystemDto, roleDto, businessDto));
        }

        this.service.create(userRoleBusinessDtos);
    }
}