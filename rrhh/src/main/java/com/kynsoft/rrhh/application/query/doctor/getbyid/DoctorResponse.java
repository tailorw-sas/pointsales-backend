package com.kynsoft.rrhh.application.query.doctor.getbyid;

import com.kynsoft.rrhh.domain.dto.DoctorDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class DoctorResponse implements IResponse {
    private UUID id;
    private String identification;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private String registerNumber;
    private String language;
    private boolean isExpress;

    public DoctorResponse(DoctorDto userSystemDto) {
        this.id = userSystemDto.getId();
        this.email = userSystemDto.getEmail();
        this.name = userSystemDto.getName();
        this.lastName = userSystemDto.getLastName();
        this.status = userSystemDto.getStatus();
        this.registerNumber = userSystemDto.getRegisterNumber();
        this.language = userSystemDto.getLanguage();
        this.isExpress = userSystemDto.isExpress();
        this.identification = userSystemDto.getIdentification();
    }

}