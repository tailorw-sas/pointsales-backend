package com.kynsof.identity.domain.dto;

import com.kynsof.share.core.domain.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class UserSystemDto {
    private UUID id;
    private String identification;
    private String email;
    private String name;
    private String lastName;
    private UserStatus status;


    private UUID idImage;
    private UserType userType;

    public UserSystemDto(UUID id, String identification, String email, String name, String lastName, UserStatus status, UUID image) {
        this.id = id;
        this.identification = identification;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.idImage = image;
    }


}
