package com.kynsof.identity.application.query.roles.getById;


import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class RoleSystemsByIdResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;

    public RoleSystemsByIdResponse(RoleDto roleDto) {
        this.id = roleDto.getId();
        this.name = roleDto.getName();
        this.description = roleDto.getDescription();

    }

}