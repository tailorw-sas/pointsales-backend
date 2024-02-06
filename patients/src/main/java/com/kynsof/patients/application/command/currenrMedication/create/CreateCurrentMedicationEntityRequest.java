package com.kynsof.patients.application.command.currenrMedication.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateCurrentMedicationEntityRequest {

    private String dosage;
    private String name;
    private UUID medicalInformationId;
}
