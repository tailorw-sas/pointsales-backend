package com.kynsof.patients.application.command.currenrMedication.update;

import com.kynsof.patients.domain.dto.enumTye.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCurrentMedicationRequest {

    private String dosage;
    private String name;
    private Status status;
}
