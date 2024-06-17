package com.kynsof.rrhh.application.query.users.getbyid;

import com.kynsof.rrhh.domain.dto.UserSystemDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    private String status;

    public UserSystemsByIdResponse(UserSystemDto userSystemDto) {
        this.id = userSystemDto.getId();
        this.userName = userSystemDto.getIdentification();
        this.email = userSystemDto.getEmail();
        this.name = userSystemDto.getName();
        this.lastName = userSystemDto.getLastName();
        this.status = userSystemDto.getStatus();
    }

}