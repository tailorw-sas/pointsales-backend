package com.kynsof.identity.domain.dto.me;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserMeDto {
    private UUID userId;
    private String userName;
    private String email;
    private String name;
    private String LastName;
    private List<BusinessRolesPermissionsDto> businesses;
}
