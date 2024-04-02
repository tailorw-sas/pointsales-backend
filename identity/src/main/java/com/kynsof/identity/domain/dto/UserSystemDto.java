package com.kynsof.identity.domain.dto;

import com.kynsof.identity.domain.dto.enumType.UserType;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserSystemDto {
    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private UserStatus status;
    private List<RoleDto> roles;

    private UUID idImage;
    private UserType userType;

    /**
     * Usar este constructor en el create
     * @param id
     * @param userName
     * @param email
     * @param name
     * @param lastName
     * @param status
     * @param roles 
     */
    public UserSystemDto(UUID id, String userName, String email, String name, String lastName, UserStatus status, List<RoleDto> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.roles = roles;
    }

}
