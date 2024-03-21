package com.kynsof.identity.application.query.permission.getById;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import com.kynsof.share.core.domain.bus.query.IResponse;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionrResponse implements IResponse {

    private UUID id;
    private String code;
    private String description;
    private PermissionStatusEnm status;
    private Set<RoleResponse> roles = new HashSet<>();

    public PermissionrResponse(PermissionDto response) {
        this.id = response.getId();
        this.code = response.getCode();
        this.description = response.getDescription();
        this.status = response.getStatus();
        response.getRoles().stream()
                .map(RoleResponse::new)
                .forEach(this.roles::add);
    }

}
