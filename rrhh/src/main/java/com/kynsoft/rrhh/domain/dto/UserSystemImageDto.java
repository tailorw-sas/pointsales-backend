package com.kynsoft.rrhh.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSystemImageDto implements Serializable {

    private UUID id;
    private byte[] image;
    private UserSystemDto user;

    public UserSystemImageDto(UUID id, byte[] image) {
        this.id = id;
        this.image = image;
    }

}
