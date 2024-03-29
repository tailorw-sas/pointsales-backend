package com.kynsof.identity.application.query.rolpermission.getbyid;

import com.kynsof.identity.application.query.business.search.BusinessResponse;
import com.kynsof.identity.application.query.roles.getById.RoleSystemsByIdResponse;
import com.kynsof.identity.application.query.users.getSearch.UserSystemsResponse;
import com.kynsof.identity.domain.dto.UserRoleBusinessDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class UserRoleBusinessResponse implements IResponse, Serializable {

    private UUID id;
    private UserSystemsResponse user;
    private RoleSystemsByIdResponse role;
    private BusinessResponse business;

    public UserRoleBusinessResponse(UserRoleBusinessDto userRoleBusinessDto) {
        this.id = userRoleBusinessDto.getId();
        this.user = new UserSystemsResponse(userRoleBusinessDto.getUser());
        this.role = new RoleSystemsByIdResponse(userRoleBusinessDto.getRole());
        this.business = new BusinessResponse(userRoleBusinessDto.getBusiness());
    }
}
