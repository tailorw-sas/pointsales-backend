package com.kynsof.identity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSystemDto {
    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private List<RolDto> roles;
}
