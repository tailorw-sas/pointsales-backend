package com.kynsof.patients.application.command.currenrMedication.update;

import com.kynsof.patients.domain.dto.EStatusPatients;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCurrentMedicationRequest {

    private String dosage;
    private String name;
    private EStatusPatients status;
}
