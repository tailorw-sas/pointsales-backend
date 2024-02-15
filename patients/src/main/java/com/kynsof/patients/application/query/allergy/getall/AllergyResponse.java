package com.kynsof.patients.application.query.allergy.getall;


import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.patients.domain.dto.AllergyEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AllergyResponse implements IResponse {
    private UUID id;
    private UUID medicalInformationId;
    private String code;
    private String name;

    public AllergyResponse(AllergyEntityDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.medicalInformationId = contactInfoDto.getMedicalInformationId();
        this.code = contactInfoDto.getCode();
        this.name = contactInfoDto.getName();
    }

}