package com.kynsof.identity.application.query.roles.getById;


import com.kynsof.identity.application.query.permission.search.PermissionSearchResponse;
import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class RoleSystemsByIdResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private List<PermissionSearchResponse> permissions;
    List<PermissionDto> permissionDtos;

    public RoleSystemsByIdResponse(RoleDto roleDto) {
        this.id = roleDto.getId();
        this.name = roleDto.getName();
        this.description = roleDto.getDescription();
        this.permissionDtos = roleDto.getPermissionDtos();
        this.permissions = roleDto.getPermissionDtos().stream().map(PermissionSearchResponse::new).collect(Collectors.toList());
    }

}