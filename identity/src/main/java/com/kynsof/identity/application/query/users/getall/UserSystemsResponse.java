package com.kynsof.identity.application.query.users.getall;


import com.kynsof.identity.domain.dto.Status;
import com.kynsof.identity.domain.dto.UserSystemDto;
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
    private Status status;

    public UserSystemsResponse(UserSystemDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.userName = contactInfoDto.getUserName();
        this.email = contactInfoDto.getEmail();
        this.name = contactInfoDto.getName();
        this.lastName = contactInfoDto.getLastName();
        this.status = contactInfoDto.getStatus();
    }

}