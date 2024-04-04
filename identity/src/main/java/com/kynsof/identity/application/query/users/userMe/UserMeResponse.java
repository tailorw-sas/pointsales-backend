package com.kynsof.identity.application.query.users.userMe;


import com.kynsof.identity.domain.dto.me.BusinessModulePermissionsDto;
import com.kynsof.identity.domain.dto.me.UserMeDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserMeResponse implements IResponse {
    private UUID userId;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private Set<BusinessModulePermissionsDto> businesses;

    public UserMeResponse(UserMeDto userMeDto) {
        this.userId = userMeDto.getUserId();
        this.userName = userMeDto.getUserName();
        this.email = userMeDto.getEmail();
        this.name = userMeDto.getName();
        this.lastName = userMeDto.getLastName();
        this.businesses = userMeDto.getBusiness();
    }
}