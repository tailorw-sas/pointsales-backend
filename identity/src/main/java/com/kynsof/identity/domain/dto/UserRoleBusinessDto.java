package com.kynsof.identity.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRoleBusinessDto {
    private UUID id;
    private UserSystemDto user;
    private RoleDto role;
    private BusinessDto business;
}
