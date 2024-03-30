package com.kynsof.identity.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserRoleBusinessDto {
    private UUID id;
    private UserSystemDto user;
    private RoleDto role;
    private BusinessDto business;
    private boolean deleted = false;

    public UserRoleBusinessDto(UUID id, UserSystemDto user, RoleDto role, BusinessDto business) {
        this.id = id;
        this.user = user;
        this.role = role;
        this.business = business;
    }

}
