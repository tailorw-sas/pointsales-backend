package com.kynsoft.rrhh.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorDto extends UserSystemDto {
    private String registerNumber;
    private String language;
    private boolean isExpress;

    public DoctorDto(UUID id, String identification, String email, String name, String lastName, String status,
                     String registerNumber, String language, boolean isExpress, String phoneNumber, String image) {
        super(id, identification, name, lastName, email, status, phoneNumber, image);
        this.registerNumber = registerNumber;
        this.language = language;
        this.isExpress = isExpress;
    }
}