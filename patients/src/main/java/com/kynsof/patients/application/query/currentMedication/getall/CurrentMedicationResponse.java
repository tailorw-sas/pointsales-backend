package com.kynsof.patients.application.query.currentMedication.getall;


import com.kynsof.patients.domain.bus.query.IResponse;
import com.kynsof.patients.domain.dto.CurrentMerdicationEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CurrentMedicationResponse implements IResponse {
    private UUID id;

    private UUID medicalInformationId;

    private String dosage;

    private String name;


    public CurrentMedicationResponse(CurrentMerdicationEntityDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.medicalInformationId = contactInfoDto.getMedicalInformationId();
        this.dosage = contactInfoDto.getDosage();
        this.name = contactInfoDto.getName();
    }

}