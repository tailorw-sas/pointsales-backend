package com.kynsof.identity.application.query.users.getById;


import com.kynsof.identity.domain.dto.RolDto;
import com.kynsof.identity.domain.dto.Status;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class UserSystemsByIdResponse implements IResponse {
    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private Status status;
    private List<RolDto> roles;

    public UserSystemsByIdResponse(UserSystemDto userSystemDto) {
        this.id = userSystemDto.getId();
        this.userName = userSystemDto.getUserName();
        this.email = userSystemDto.getEmail();
        this.name = userSystemDto.getName();
        this.lastName = userSystemDto.getLastName();
        this.status = userSystemDto.getStatus();
        this.roles = userSystemDto.getRoles();
    }

}