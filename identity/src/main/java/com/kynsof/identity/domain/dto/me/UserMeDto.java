package com.kynsof.identity.domain.dto.me;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserMeDto {
    private UUID userId;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private String image;
    private Set<BusinessModulePermissionsDto> business;
}
