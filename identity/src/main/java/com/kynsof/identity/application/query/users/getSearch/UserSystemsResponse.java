package com.kynsof.identity.application.query.users.getSearch;


import com.kynsof.identity.domain.dto.UserStatus;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.dto.enumType.UserType;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class UserSystemsResponse implements IResponse {
    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private UserStatus status;
    private UserType userType;
    private UUID image;

    public UserSystemsResponse(UserSystemDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.userName = contactInfoDto.getIdentification();
        this.email = contactInfoDto.getEmail();
        this.name = contactInfoDto.getName();
        this.lastName = contactInfoDto.getLastName();
        this.status = contactInfoDto.getStatus();
        this.image = contactInfoDto.getIdImage() != null ? contactInfoDto.getIdImage() : null;
        this.userType = contactInfoDto.getUserType() != null ? contactInfoDto.getUserType() : UserType.UNDEFINED;
    }

}