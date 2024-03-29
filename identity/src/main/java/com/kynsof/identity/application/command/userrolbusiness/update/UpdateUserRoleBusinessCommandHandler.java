package com.kynsof.identity.application.command.userrolbusiness.update;

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

@Component
public class UpdateUserRoleBusinessCommandHandler implements ICommandHandler<UpdateUserRoleBusinessCommand> {

    private final IUserRoleBusinessService service;

    private final IRoleService roleService;

    private final IBusinessService businessService;

    private final IUserSystemService userSystemService;

    public UpdateUserRoleBusinessCommandHandler(IUserRoleBusinessService service, 
                                             IRoleService roleService, 
                                             IBusinessService businessService,
                                             IUserSystemService userSystemService) {
        this.service = service;
        this.roleService = roleService;
        this.businessService = businessService;
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(UpdateUserRoleBusinessCommand command) {
        List<UserRoleBusinessDto> userRoleBusinessDtos = new ArrayList<>();

        for (UserRoleBusinessUpdateRequest userRoleBusinessRequest : command.getPayload()) {
            
            UserRoleBusinessDto toUpdate = this.service.findById(userRoleBusinessRequest.getId());
            
            UserSystemDto userSystemDto = this.userSystemService.findById(userRoleBusinessRequest.getUser());
            RoleDto roleDto = this.roleService.findById(userRoleBusinessRequest.getRole());
            BusinessDto businessDto = this.businessService.findById(userRoleBusinessRequest.getBusiness());

            UserRoleBusinessDto payloadUpdate = new UserRoleBusinessDto(userRoleBusinessRequest.getId(), userSystemDto, roleDto, businessDto);
            if (validate(payloadUpdate, toUpdate)) {
                userRoleBusinessDtos.add(payloadUpdate);
            }

        }

        this.service.update(userRoleBusinessDtos);
    }

    private boolean validate(UserRoleBusinessDto payloadUpdate, UserRoleBusinessDto toUpdate) {
        return !(payloadUpdate.getBusiness().getId().equals(toUpdate.getBusiness().getId()) &&
                payloadUpdate.getRole().getId().equals(toUpdate.getRole().getId()) &&
                payloadUpdate.getUser().getId().equals(toUpdate.getUser().getId()));
    }
}