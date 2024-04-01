package com.kynsof.identity.application.query.roles.getSearch;


import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.enumType.RoleStatusEnm;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class RoleSystemsResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private RoleStatusEnm status;

    public RoleSystemsResponse(RoleDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.name = contactInfoDto.getName();
        this.description = contactInfoDto.getDescription();
        this.status = contactInfoDto.getStatus();
    }

}