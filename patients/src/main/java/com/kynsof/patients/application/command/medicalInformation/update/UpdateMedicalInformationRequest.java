package com.kynsof.patients.application.command.medicalInformation.update;

import com.kynsof.patients.domain.dto.EStatusPatients;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMedicalInformationRequest {

    private String bloodType;
    private String medicalHistory;
    private EStatusPatients status;
}
