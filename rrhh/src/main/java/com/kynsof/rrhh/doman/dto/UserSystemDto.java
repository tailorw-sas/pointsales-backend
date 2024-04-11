package com.kynsof.rrhh.doman.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSystemDto {
    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String email;
    private String status;
    private UserSystemImageDto image;

    public UserSystemDto(UUID id, String identification, String name, String lastName, String email, String status) {
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }

}
