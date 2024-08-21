package com.kynsoft.rrhh.application.query.assistant.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsoft.rrhh.domain.dto.AssistantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AssistantResponse implements IResponse {
    private UUID id;
    private String identification;
    private String code;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private String phoneNumber;
    private String image;
    private String department;

    public AssistantResponse(AssistantDto dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.status = dto.getStatus();
        this.identification = dto.getIdentification();
        this.code = dto.getCode();
        this.phoneNumber = dto.getPhoneNumber();
        this.image = dto.getImage();
        this.department = dto.getDepartment();
    }

}