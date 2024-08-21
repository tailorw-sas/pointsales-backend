package com.kynsoft.rrhh.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AssistantDto extends UserSystemDto {
    private String department;
    public AssistantDto(UUID id, String identification, String code, String email, String name, String lastName, String status,
                        String phoneNumber, String image, String department) {
        super(id, identification, code, name, lastName, email, status, phoneNumber, image);
        this.department = department;
    }

}